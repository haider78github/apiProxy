package com.softech.ls360.web.proxy.service.impl.lms;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.softech.ls360.lms.proxy.entities.CustomerEntitlement;
import com.softech.ls360.lms.proxy.repositories.CustomerEntitlementRepository;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.Customer;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CustomerEntitlementError;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CustomerEntitlementRequest;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CustomerEntitlements;
import com.softech.ls360.web.proxy.endpoint.model.response.entitlement.customer.CustomerEntitlementResponse;
import com.softech.ls360.web.proxy.service.MessageService;
import com.softech.ls360.web.proxy.service.lms.CustomerEntitlementRequestService;

@Service
public class CustomerEntitlementRequestServiceImpl implements CustomerEntitlementRequestService {

	@Inject
	private CustomerEntitlementRepository  customerEntitlementRepository;
	
	@Inject
	private MessageService messageService;
	
	@Override
	public CustomerEntitlementResponse getCustomerEntitlement(CustomerEntitlementRequest request) throws Exception {
		
		List<CustomerEntitlement> customerEntitlementsList = null;
		Customer customer = request.getCustomer();
		String customerGuid = customer.getCustomerGuid();
		String customerCode = customer.getCustomerCode();
		
		if (StringUtils.isBlank(customerGuid) && StringUtils.isBlank(customerCode)) {
			String errorMessage = messageService.getLocalizeMessage("error.invalid.request.entitlement", new Object[]{"CustomerGuid", "CustomerCode"});
			throw new Exception(errorMessage);
		}
		
		if (StringUtils.isNotBlank(customerGuid)) {
			customerEntitlementsList = customerEntitlementRepository.findByCustomerCustomerGuid(customerGuid);
		} else if (StringUtils.isNotBlank(customerCode)) {
			customerEntitlementsList = customerEntitlementRepository.findByCustomerCustomerCode(customerCode);
		}
		
		if (CollectionUtils.isEmpty(customerEntitlementsList)) {
			if (StringUtils.isNotBlank(customerGuid)) {
				return getCustomerEntitlementResponse(customerGuid);
			} else if (StringUtils.isNotBlank(customerCode)) {
				return getCustomerEntitlementResponse(customerCode);
			}
		}
		
		return getCustomerEntitlementResponse(customerEntitlementsList);
	}

	@Override
	public CustomerEntitlementResponse getEntitlementErrorResponse(Exception e) {
		
		String errorMessage = e.getMessage();
		CustomerEntitlementError entitlementError = new CustomerEntitlementError();
		entitlementError.setErrorCode("1");
		entitlementError.setErrorMessage(errorMessage);
		CustomerEntitlementResponse customerEntitlementResponse = new CustomerEntitlementResponse();
		customerEntitlementResponse.setEntitlementError(entitlementError);
		return customerEntitlementResponse;
		
	}
	
	private CustomerEntitlementResponse getCustomerEntitlementResponse(String customerGuid) {
		
		CustomerEntitlementResponse response = new CustomerEntitlementResponse();
		CustomerEntitlements customerEntitlements = new CustomerEntitlements();
		String message = messageService.getLocalizeMessage("message.entitlements.customer.none");
		customerEntitlements.setMessage(message + customerGuid);
		response.setCustomerEntitlements(customerEntitlements);
		return response;
		
	}
	
	private CustomerEntitlementResponse getCustomerEntitlementResponse(List<CustomerEntitlement> customerEntitlementsList) {
		
		CustomerEntitlementResponse response = new CustomerEntitlementResponse();
		CustomerEntitlements customerEntitlements = new CustomerEntitlements();
		customerEntitlements.setCustomerEntitlementsList(customerEntitlementsList);
		response.setCustomerEntitlements(customerEntitlements);
		return response;
		
	}

}
