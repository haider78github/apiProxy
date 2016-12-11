package com.softech.ls360.web.proxy.endpoint.restful;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softech.ls360.web.proxy.config.annotation.RestEndpoint;
import com.softech.ls360.web.proxy.endpoint.model.request.vu360user.UpdateUserRequest;
import com.softech.ls360.web.proxy.endpoint.model.request.vu360user.UserRequest;
import com.softech.ls360.web.proxy.security.RestUserPrincipal;
import com.softech.ls360.web.proxy.service.lmsapi.WebProxyLmsApiUserService;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.user.AddUserResponse;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.user.UpdateUserResponse;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.UpdateableUser;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.User;

@RestEndpoint
@RequestMapping(value="/lms/customer/")
public class VU360UserRestEndpoint {

	private static final Logger logger = LogManager.getLogger();
	
	@Inject
	private WebProxyLmsApiUserService webProxyLmsApiUserService;
	
	@RequestMapping(value = "user/add", method = RequestMethod.POST)
	@ResponseBody
	public AddUserResponse addUser(@RequestBody @Valid UserRequest user,
			@RequestHeader(value="key", required=true) String clientApiKey,
			@AuthenticationPrincipal RestUserPrincipal principal) throws Exception {
	
		logger.info("Request received at " + getClass().getName() + " for add user");
	
		Long customerId = principal.getCustomerId();
		String customerCode = principal.getCustomerCode();
		User lmsApiUser = user.getUser();
		AddUserResponse addUserResponse = webProxyLmsApiUserService.createUser(lmsApiUser, customerId, customerCode, clientApiKey);
		return addUserResponse;
		
	}
	
	@RequestMapping(value = "user/update", method = RequestMethod.PUT)
	@ResponseBody
	public UpdateUserResponse updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest,
			@RequestHeader(value="key", required=true) String clientApiKey,
			@AuthenticationPrincipal RestUserPrincipal principal) throws Exception {
		
		logger.info("Request received at " + getClass().getName() + " update user");
		
		Long customerId = principal.getCustomerId();
		String customerCode = principal.getCustomerCode();
		UpdateableUser lmsApiUser = updateUserRequest.getUpdateableUser();
		UpdateUserResponse updateUserResponse = webProxyLmsApiUserService.updateUser(lmsApiUser, customerId, customerCode, clientApiKey);
		return updateUserResponse;
	
	}
	
}
