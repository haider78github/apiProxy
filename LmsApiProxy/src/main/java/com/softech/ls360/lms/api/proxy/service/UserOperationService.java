package com.softech.ls360.lms.api.proxy.service;

import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.user.AddUserRequest;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.user.AddUserResponse;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.user.UpdateUserRequest;
import com.softech.vu360.lms.webservice.message.lmsapi.serviceoperations.user.UpdateUserResponse;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.RegisterUser;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.UpdateableUser;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.User;

public interface UserOperationService {

	AddUserRequest getAddUserRequest(User user, String customerCode, String apiKey) throws Exception;
	UpdateUserRequest getUpdateUserRequest(UpdateableUser updateableUser, String customerCode, String apiKey) throws Exception;
	RegisterUser getRegisterUser(AddUserResponse addUserResponse) throws Exception;
	RegisterUser getRegisterUser(UpdateUserResponse updateUserResponse) throws Exception;
	boolean isOrganizationGroupExist(User user) throws Exception;
	boolean isOrganizationGroupExist(UpdateableUser updateableUser) throws Exception;
	UpdateableUser getUpdateableUser(User user) throws Exception;
	
	
	
}
