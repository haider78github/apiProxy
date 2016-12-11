package com.softech.ls360.web.proxy.endpoint.restful;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softech.ls360.web.proxy.config.annotation.RestEndpoint;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CustomerEntitlementDetailsRequest;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CustomerEntitlementRequest;
import com.softech.ls360.web.proxy.endpoint.model.response.entitlement.customer.CustomerEntitlementDetailsResponse;
import com.softech.ls360.web.proxy.endpoint.model.response.entitlement.customer.CustomerEntitlementResponse;
import com.softech.ls360.web.proxy.service.lms.CustomerEntitlementDetailsRequestService;
import com.softech.ls360.web.proxy.service.lms.CustomerEntitlementRequestService;

@RestEndpoint
@RequestMapping(value="/lms/customer/entitlement")
public class CustomerEntitlementRestEndpoint {

	private static final Logger logger = LogManager.getLogger();
	
	@Inject
	private CustomerEntitlementRequestService customerEntitlementRequestService;
	
	@Inject
	private CustomerEntitlementDetailsRequestService customerEntitlementDetailsRequestService;
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public CustomerEntitlementResponse getCustomerEntitlements(@RequestBody @Valid CustomerEntitlementRequest request)  {
		
		logger.info("Request received at " + getClass().getName() + " for entitlement");
		
		try {
			return customerEntitlementRequestService.getCustomerEntitlement(request);
		} catch (Exception e) {
			return customerEntitlementRequestService.getEntitlementErrorResponse(e);
		}
	}
	
	@RequestMapping(value = "/details/get", method = RequestMethod.POST)
	@ResponseBody
	public CustomerEntitlementDetailsResponse getCustomerEntitlementDetails(@RequestBody @Valid CustomerEntitlementDetailsRequest request)  {
		
		logger.info("Request received at " + getClass().getName() + " for entitlement details");
		
		try {
			return customerEntitlementDetailsRequestService.getCustomerEntitlementDetails(request);
		} catch (Exception e) {
			return customerEntitlementDetailsRequestService.getEntitlementErrorResponse(e);
		}
	}
	
}
