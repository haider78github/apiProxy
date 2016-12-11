package com.softech.ls360.web.proxy.service.lms;

import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor.DistributorEntitlementRequest;
import com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor.WebProxyDistributorEntitlementResponse;

public interface DistributorEntitlementRequestService {

	WebProxyDistributorEntitlementResponse getDistributorEntitlement(DistributorEntitlementRequest request) throws Exception;
	WebProxyDistributorEntitlementResponse getRequestError(Exception e);
	
}
