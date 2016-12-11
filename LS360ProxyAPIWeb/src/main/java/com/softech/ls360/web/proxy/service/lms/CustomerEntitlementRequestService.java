package com.softech.ls360.web.proxy.service.lms;

import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CustomerEntitlementRequest;
import com.softech.ls360.web.proxy.endpoint.model.response.entitlement.customer.CustomerEntitlementResponse;

public interface CustomerEntitlementRequestService {

	CustomerEntitlementResponse getCustomerEntitlement(CustomerEntitlementRequest request) throws Exception;
	CustomerEntitlementResponse getEntitlementErrorResponse(Exception e);
	
}
