package com.softech.ls360.lms.api.proxy.service;

import java.util.List;

import com.softech.vu360.lms.webservice.message.lmsapi.types.orggroup.RegisterOrganizationalGroup;
import com.softech.vu360.lms.webservice.message.lmsapi.types.user.RegisterUser;

public interface LmsApiRegisterUserService {

	boolean isSuccessfulRegisterUser(RegisterUser registerUser);
	List<RegisterOrganizationalGroup> getRegisterUserFailedOrganizationalGroupsList(RegisterUser registerUser);
	
	
}
