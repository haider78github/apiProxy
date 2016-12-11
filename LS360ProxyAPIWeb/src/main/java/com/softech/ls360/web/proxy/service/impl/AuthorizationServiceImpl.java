package com.softech.ls360.web.proxy.service.impl;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softech.ls360.lms.proxy.entities.ApiFrequencyRestriction;
import com.softech.ls360.lms.proxy.entities.Customer;
import com.softech.ls360.lms.proxy.entities.Distributor;
import com.softech.ls360.lms.proxy.entities.LmsApiCustomer;
import com.softech.ls360.lms.proxy.repositories.ApiFrequencyRestrictionRepository;
import com.softech.ls360.lms.proxy.repositories.LmsApiCustomerRepository;
import com.softech.ls360.web.proxy.exception.restful.RestException;
import com.softech.ls360.web.proxy.service.AuthorizationService;
import com.softech.ls360.web.proxy.service.MessageService;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	private static final Logger logger = LogManager.getLogger();
	
	@Inject
	private ApiFrequencyRestrictionRepository apiFrequencyRestrictionRepository;
	
	@Inject
	private LmsApiCustomerRepository lmsApiCustomerRepository;
	
	@Inject
	private MessageService messageService;
	
	@Override
	public Customer authorizeApiKey(String clientApiKey, Long distributorId) {
		
		LmsApiCustomer lmsApiCustomer = lmsApiCustomerRepository.findByApiKey(clientApiKey);
		if (lmsApiCustomer == null) {
			throw new RestException("error.api.authorization", getLocalizeMessage("error.api.authorization"));
		}
		String lmsApiCustomerApiKey = lmsApiCustomer.getApiKey();
		isValidApiKey(clientApiKey, lmsApiCustomerApiKey);
		
		Customer customer = lmsApiCustomer.getCustomer();
		
		if (customer == null) {
			throw new RestException("error.api.authorization", getLocalizeMessage("error.api.authorization"));
		}
		
		Long customerDistributorId =  customer.getDistributor().getId();
		
		if (!distributorId.equals(customerDistributorId)) {
			throw new RestException("error.customer.reseller.association", getLocalizeMessage("error.customer.reseller.association"));
		}
		logger.info("Finished authenticateWithKey");
		return customer;
	}

	@Override
	public boolean authorizeUpdatePrivilege(String lmsApiDistributorApiKey, String clientApiKey, String privileges) {
		
		if (privileges == null) {
			throw new RestException("error.privilege.operation", getLocalizeMessage("error.privilege.operation"));
		}
		
		isValidApiKey(lmsApiDistributorApiKey, clientApiKey);
		LmsApiDistributorPrivileges lmsApiDistributorPrivileges = getLmsApiDistributorPrivileges(privileges);
		if (!lmsApiDistributorPrivileges.isCourseStatusUpdate()) {
			throw new RestException("error.privilege.operation.update", getLocalizeMessage("error.privilege.operation.update"));
		}
		logger.info("Finished authenticateWithUpdatePrivilege");
		return true;
	}

	@Override
	public boolean authorizeOperationAllowFrequency(Long distributorId, String lmsApiDistributorApiKey, String distributorAllowFrequency, String clientApiKey, String operation) {
		
		isValidApiKey(lmsApiDistributorApiKey, clientApiKey);
		authorize(distributorId, operation, distributorAllowFrequency);
		return true;
		
	}
	
	private boolean authorize(Long distributorId, String operation, String distributorAllowFrequency)  {
		logger.info("Starting authorize");
		
		ApiFrequencyRestriction apiFrequencyRestriction = apiFrequencyRestrictionRepository.findByDistributorIdAndOperation(distributorId, operation);
		if (apiFrequencyRestriction == null) {
			Distributor distributor = new Distributor();
			distributor.setId(distributorId);
			insertApiFrequencyRestriction(distributor, operation);
		} else {
			
			LocalDateTime lastCall = apiFrequencyRestriction.getLastCall();
			boolean isAllow = checkAllowFrequency(distributorAllowFrequency, lastCall);
			if (isAllow) {
				updateApiFrequencyRestriction(apiFrequencyRestriction);
			} else {
				String distributorInterval = getAllowFrequencyInterval(distributorAllowFrequency);
				throw new RestException("error.api.frequency.restriction", getLocalizeMessage("error.api.frequency.restriction", new Object[]{operation, distributorAllowFrequency, distributorInterval}));
			}
		}
		logger.info("Finished authorize");
		return true;
	}

	private boolean insertApiFrequencyRestriction(Distributor distributor, String operation)  {
		
		logger.info("Inserting API Frequency Restriction");
		LocalDateTime lastCall = LocalDateTime.now();
		ApiFrequencyRestriction apiFreqRestriction = new ApiFrequencyRestriction();
		apiFreqRestriction.setDistributor(distributor);
		apiFreqRestriction.setOperation(operation);
		apiFreqRestriction.setLastCall(lastCall);
		apiFrequencyRestrictionRepository.save(apiFreqRestriction);
		logger.info("Inserted");
		return true;
	}
	
	private boolean updateApiFrequencyRestriction(ApiFrequencyRestriction apiFrequencyRestriction)  {
		
		logger.info("Updating API Frequency Restriction");
		apiFrequencyRestriction.setLastCall(LocalDateTime.now());
		apiFrequencyRestrictionRepository.save(apiFrequencyRestriction);
		logger.info("Updated");
		return true;
	}
	
	private boolean checkAllowFrequency(String allowFrequency, LocalDateTime lastCall)  {
		
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime operationLastCall = lastCall;
		
		long lastCallFreqInMin = Duration.between(operationLastCall, currentDate).toMinutes();
		long allowFrequencyInMin = getAllowFrequencyInMins(allowFrequency);
		
		if (lastCallFreqInMin < allowFrequencyInMin) {
			return false;
		} 
		return true;
	}
	
	private long getAllowFrequencyInMins(String allowFrequency) {
		
		long frequencyInMin = -1;
		String allowFreq = removeLastCharacter(allowFrequency);
		
		if (allowFrequency.indexOf("m") != -1) {
			frequencyInMin = Long.parseLong(allowFreq);
		}
		
		if (allowFrequency.indexOf("h") != -1) {
			frequencyInMin = Long.parseLong(allowFreq) * 60;
		}
		
		if (allowFrequency.indexOf("d") != -1) {
			frequencyInMin = Long.parseLong(allowFreq) * 24 * 60;
		}
		
		if (allowFrequency.indexOf("w") != -1) {
			frequencyInMin = Long.parseLong(allowFreq) * 7 * 24 * 60;
		}
		return frequencyInMin;
	}
	
	public String removeLastCharacter(String str) {
		if (str.length() > 0) {
			str = str.substring(0, str.length()-1);
		}
		return str;
	}
	
	private String getAllowFrequencyInterval(String allowFrequency) {
		
		String allowFrequencyInterval = null;
		if (allowFrequency.indexOf("m") != -1) {
			allowFrequencyInterval = "minutes";
		}
		
		if (allowFrequency.indexOf("h") != -1) {
			allowFrequencyInterval = "hours";
		}
		
		if (allowFrequency.indexOf("d") != -1) {
			allowFrequencyInterval = "days";
		}
		
		if (allowFrequency.indexOf("w") != -1) {
			allowFrequencyInterval = "weeks";
		}
		return allowFrequencyInterval;
	}

	private boolean isValidApiKey(String clientApiKey, String apiKey) throws RestException {
		
		if (!clientApiKey.equals(apiKey)) {
			throw new RestException("error.api.authorization", getLocalizeMessage("error.api.authorization"));
		}
		return true;
	}
	
	private LmsApiDistributorPrivileges getLmsApiDistributorPrivileges(String json)  {
		
		String errorMessage = null;
		LmsApiDistributorPrivileges lmsApiDistributorPrivileges = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			lmsApiDistributorPrivileges =  mapper.readValue(json, LmsApiDistributorPrivileges.class); 
		} catch (IOException e) {
			errorMessage = e.getMessage();
			throw new RestException("error.json.parse.LmsApiDistributorPrivileges" ,errorMessage);
	    } 
		return lmsApiDistributorPrivileges;
	}
	
	private class LmsApiDistributorPrivileges {
		
		private boolean courseStatusUpdate;

		public boolean isCourseStatusUpdate() {
			return courseStatusUpdate;
		}

	}
	
	private String getLocalizeMessage(String code) {
		String errorMessage = "";
		if (StringUtils.isNotBlank(code)) {
			errorMessage = messageService.getLocalizeMessage(code);
		}
		return errorMessage;
	}
	
	private String getLocalizeMessage(String code, Object... arguments) {
		String errorMessage = "";
		if (StringUtils.isNotBlank(code)) {
			errorMessage = messageService.getLocalizeMessage(code, arguments);
		}
		return errorMessage;
	}
	
}
