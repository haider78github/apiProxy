package com.softech.ls360.web.proxy.service.lms;

import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor.WebProxyDistributorEntitlementDetailsRequest;
import com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor.WebProxyDistributorEntitlementDetailsResponse;

public interface DistributorEntitlementDetailsRequestService {

	WebProxyDistributorEntitlementDetailsResponse getDistributorEntitlementDetails(WebProxyDistributorEntitlementDetailsRequest request) throws Exception;
	WebProxyDistributorEntitlementDetailsResponse getRequestError(Exception e);
	
}
