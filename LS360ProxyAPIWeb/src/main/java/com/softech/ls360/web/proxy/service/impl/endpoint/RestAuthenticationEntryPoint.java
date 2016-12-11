package com.softech.ls360.web.proxy.service.impl.endpoint;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softech.ls360.web.proxy.exception.restful.RestAuthenticationException;
import com.softech.ls360.web.proxy.exception.restful.RestExceptionHandler.ErrorItem;
import com.softech.ls360.web.proxy.exception.restful.RestExceptionHandler.ErrorResponse;

@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Inject
	private ObjectMapper objectMapper;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		
		String ERROR_UNAUTHORIZED = "Unauthorized";
		
		String errorResponse = "";
		String errorCode = "";
		String errorMessage = "";
		String status = "";
		if (authException != null) {
			if (authException instanceof RestAuthenticationException) {
				RestAuthenticationException restAuthException = (RestAuthenticationException)authException;
				status = String.valueOf(HttpServletResponse.SC_UNAUTHORIZED);
				errorCode = restAuthException.getErrorCode();
				errorMessage = restAuthException.getErrorMessage();
			} else if (authException instanceof BadCredentialsException) {
				BadCredentialsException exception = (BadCredentialsException)authException;
				status = String.valueOf(HttpServletResponse.SC_UNAUTHORIZED);
				errorCode = ERROR_UNAUTHORIZED;
				errorMessage = exception.getMessage();
			} else {
				Throwable exceptionCause = authException.getCause();
				if (exceptionCause != null) {
					if (exceptionCause instanceof JpaSystemException) {
						status = String.valueOf(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						errorCode =  "error.database.connection";
						errorMessage = exceptionCause.getMessage();
					} else if (exceptionCause instanceof RestAuthenticationException) {
						RestAuthenticationException restAuthException = (RestAuthenticationException)exceptionCause;
						status = String.valueOf(HttpServletResponse.SC_UNAUTHORIZED);
						errorCode = restAuthException.getErrorCode();
						errorMessage = restAuthException.getErrorMessage();
					}
				}
			}
		}
		
		String accept = request.getHeader("Accept");
		if (StringUtils.isBlank(accept)) {
			accept = "application/json";
			response.setContentType("application/json");
		}
		if (accept.equalsIgnoreCase("application/json")) {
			errorResponse = getJsonErrorResponse(status, errorCode, errorMessage);
		}
		
		PrintWriter writer = response.getWriter();
		writer.println(errorResponse);
	}
	
	private String getJsonErrorResponse(String status, String errorCode, String errorMessage) throws JsonProcessingException {
		
		String errorResponse = "";
		if (StringUtils.isNotBlank(errorCode) && StringUtils.isNotBlank(errorMessage)) {
			ErrorResponse errors = new ErrorResponse();
			
			ErrorItem error = new ErrorItem();
			error.setStatus(status);
			error.setCode(errorCode);
			error.setMessage(errorMessage);
			
			errors.addError(error);
			errorResponse = objectMapper.writeValueAsString(errors);
		}
		return errorResponse;
		
	}

}
