package com.softech.ls360.web.proxy.service;

import org.springframework.validation.annotation.Validated;

import com.softech.ls360.lms.proxy.entities.Customer;
import com.softech.ls360.web.proxy.validation.NotBlank;


@Validated
public interface AuthorizationService {

	Customer authorizeApiKey(
			@NotBlank(message = "{validate.api.key}")String clientApiKey, Long distributorId) ;
	
	boolean authorizeUpdatePrivilege(String lmsApiDistributorApiKey, String clientApiKey, String privileges);
	
	boolean authorizeOperationAllowFrequency(Long distributorId, String lmsApiDistributorApiKey, String distributorAllowFrequency, String clientApiKey, String operation);
	
}
