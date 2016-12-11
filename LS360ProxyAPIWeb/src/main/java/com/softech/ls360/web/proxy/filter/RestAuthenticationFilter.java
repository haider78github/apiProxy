package com.softech.ls360.web.proxy.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.softech.ls360.lms.proxy.entities.Customer;
import com.softech.ls360.web.proxy.exception.restful.RestAuthenticationException;
import com.softech.ls360.web.proxy.exception.restful.RestException;
import com.softech.ls360.web.proxy.security.RestUserPrincipal;
import com.softech.ls360.web.proxy.service.AuthorizationService;
import com.softech.ls360.web.proxy.service.MessageService;

@Component("restAuthenticationFilter")
public class RestAuthenticationFilter extends GenericFilterBean {

	private static final Logger logger = LogManager.getLogger();
	
	@Inject
	private AuthenticationManager authenticationManager;
	
	@Inject
	private AuthorizationService webProxyAuthorizationService;
	
	@Inject
	private AuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Inject
	private MessageService messageService;
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
		
        String requestURI =  httpRequest.getRequestURI();
        logger.info("Get request: " + requestURI);
        if (requestURI.equalsIgnoreCase("/LS360ProxyAPIWeb/restful/sso/cas/lf")) {
        	httpRequest.getRequestDispatcher("/sso/cas/lf").forward(httpRequest, httpResponse);
        } else {
        	try {
        		Authentication successfulAuthentication = authenticateUser(httpRequest);
            	authorizeUser(httpRequest, requestURI, successfulAuthentication);
        		SecurityContextHolder.getContext().setAuthentication(successfulAuthentication);
        		logger.info("Authentication successful. Start Processing request: " + requestURI);
            	chain.doFilter(httpRequest, httpResponse);
        	} catch (AuthenticationException authException) {
        		logger.info("Authentication failure: " + authException.getMessage());
        		restAuthenticationEntryPoint.commence(httpRequest, httpResponse, authException);		
        	}
        }
        
	}
	
	private Authentication authenticateUser(HttpServletRequest httpRequest) throws RestException {
		
		String authorization = httpRequest.getHeader("Authorization");
		if (authorization == null) {
			 throw new RestAuthenticationException("error.authorization.header", getLocalizeMessage("error.authorization.header"));
		}
		 
		 isValidAuthorizationHeader(authorization);
		 int index = authorization.indexOf(" ");
		 if (index == -1 ) {
			 throw new RestAuthenticationException("error.authorization.header", getLocalizeMessage("error.authorization.header"));
		 }
		 String encodedCredentials = authorization.substring(index + 1, authorization.length());
		 String[] decodedCredentials = decodeBasicAuthorizationCredentials(encodedCredentials);
		 if (decodedCredentials.length != 2) {
		    	throw new RestAuthenticationException("error.authorization.header", getLocalizeMessage("error.authorization.header"));
		 }
		    
		String userName = decodedCredentials[0];
		String password = decodedCredentials[1];
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(userName, password);
		Authentication successfulAuthentication = authenticationManager.authenticate(authentication);
		return successfulAuthentication;
		
	
	}
	
	private void authorizeUser(HttpServletRequest httpRequest, String requestURI, Authentication successfulAuthentication)  {
		
		if (requestURI.startsWith("/LS360ProxyAPIWeb/restful/lms/reseller/")) {
			return;
		}
		
		RestUserPrincipal principal = (RestUserPrincipal)successfulAuthentication.getPrincipal();
    	String clientApiKey = httpRequest.getHeader("key");
		if (StringUtils.isBlank(clientApiKey)) {
			throw new RestAuthenticationException("validate.header.key", getLocalizeMessage("validate.header.key"));
		}
		
		try {
			if (requestURI.endsWith("/stats/update")) {
	    		String lmsApiDistributorApiKey = principal.getApiKey();
	    		String privilege = principal.getPrivilege();
	    		webProxyAuthorizationService.authorizeUpdatePrivilege(lmsApiDistributorApiKey, clientApiKey, privilege);
	    	} else {
	    		Long distributorId = principal.getDistributorId();
	    		Customer customer = webProxyAuthorizationService.authorizeApiKey(clientApiKey, distributorId);
	    		Long customerId = customer.getId();
	    		String customerCode = customer.getCustomerCode();
	    		principal.setCustomerId(customerId);
	    		principal.setCustomerCode(customerCode);
	    	}	
		} catch (RestException e) {
			String errorCode = e.getErrorCode();
			String errorMessage = e.getErrorMessage();
			throw new RestAuthenticationException(errorCode, errorMessage);
		}
	}
	
	private boolean isValidAuthorizationHeader(String authorization) {
		
		if (!authorization.startsWith("Basic ")) {
			throw new RestAuthenticationException("error.authorization.header", getLocalizeMessage("error.authorization.header"));
		}
		return true;
	}
	
	private String decodeBase64(String encoded) throws RestException {

	   	 byte[] encodedBytes = encoded.getBytes();
	   	 byte[] decodeBase64 = Base64.decodeBase64(encodedBytes);
	   	 return new String(decodeBase64);
	}
	
	private String[] decodeBasicAuthorizationCredentials(String encodedCredentials)   {

		String[] decodedCredentials = null;
		boolean isBase64 = Base64.isBase64(encodedCredentials.getBytes());
		if (isBase64) {
			String decodedAuthorization = decodeBase64(encodedCredentials);
			decodedCredentials = decodedAuthorization.split(":");
		} else {
			decodedCredentials = encodedCredentials.split(":");
		}
		return decodedCredentials;
    }
	
	private String getLocalizeMessage(String code) {
		String errorMessage = "";
		if (StringUtils.isNotBlank(code)) {
			errorMessage = messageService.getLocalizeMessage(code);
		}
		return errorMessage;
	}
		
}
