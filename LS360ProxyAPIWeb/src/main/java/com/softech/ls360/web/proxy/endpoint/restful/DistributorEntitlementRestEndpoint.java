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
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor.DistributorEntitlementRequest;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor.WebProxyDistributorEntitlementDetailsRequest;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor.WebProxyDistributorEntitlementRequest;
import com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor.WebProxyDistributorEntitlementDetailsResponse;
import com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor.WebProxyDistributorEntitlementResponse;
import com.softech.ls360.web.proxy.service.lms.DistributorEntitlementDetailsRequestService;
import com.softech.ls360.web.proxy.service.lms.DistributorEntitlementRequestService;

@RestEndpoint
@RequestMapping(value="/lms/distributor/entitlement")
public class DistributorEntitlementRestEndpoint {

	private static final Logger logger = LogManager.getLogger();
	
	@Inject
	private DistributorEntitlementRequestService distributorEntitlementRequestService;
	
	@Inject
	private DistributorEntitlementDetailsRequestService distributorEntitlementDetailsRequestService;
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public WebProxyDistributorEntitlementResponse getCustomerEntitlements(@RequestBody @Valid WebProxyDistributorEntitlementRequest request) {
		
		logger.info("Request received at " + getClass().getName() + " for entitlement");
		
		try {
			DistributorEntitlementRequest distributorEntitlementRequest = request.getDistributorEntitlementRequest();;
			return distributorEntitlementRequestService.getDistributorEntitlement(distributorEntitlementRequest);
		} catch (Exception e) {
			return distributorEntitlementRequestService.getRequestError(e);
		}
	}
	
	@RequestMapping(value = "/details/get", method = RequestMethod.POST)
	@ResponseBody
	public WebProxyDistributorEntitlementDetailsResponse getCourseGroupDetails(@RequestBody @Valid WebProxyDistributorEntitlementDetailsRequest request)  {
		
		logger.info("Request received at " + getClass().getName() + " for entitlement details");
		
		try {
			return distributorEntitlementDetailsRequestService.getDistributorEntitlementDetails(request);
		} catch (Exception e) {
			return distributorEntitlementDetailsRequestService.getRequestError(e);
		}
	}
	
}
