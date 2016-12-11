package com.softech.ls360.web.proxy.service.lms;

import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CustomerEntitlementDetailsRequest;
import com.softech.ls360.web.proxy.endpoint.model.response.entitlement.customer.CustomerEntitlementDetailsResponse;

public interface CustomerEntitlementDetailsRequestService {

	CustomerEntitlementDetailsResponse getCustomerEntitlementDetails(CustomerEntitlementDetailsRequest request) throws Exception;
	CustomerEntitlementDetailsResponse getEntitlementErrorResponse(Exception e);
	
}
