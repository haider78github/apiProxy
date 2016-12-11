package com.softech.ls360.web.proxy.service.impl.lms;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.softech.ls360.lms.proxy.entities.DistributorEntitlement;
import com.softech.ls360.lms.proxy.repositories.DistributorEntitlementRepository;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor.Distributor;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor.DistributorEntitlementRequest;
import com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor.DistributorEntitlementResponse;
import com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor.DistributorEntitlements;
import com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor.WebProxyDistributorEntitlementResponse;
import com.softech.ls360.web.proxy.service.lms.DistributorEntitlementRequestService;

@Service
public class DistributorEntitlementRequestServiceImpl implements DistributorEntitlementRequestService {

	@Inject
	private DistributorEntitlementRepository distributorEntitlementRepository;
	
	@Override
	public WebProxyDistributorEntitlementResponse getDistributorEntitlement(DistributorEntitlementRequest request) throws Exception {
		
		Distributor reqDistributor = request.getDistributor();
		Long distributorId = reqDistributor.getId();
		List<DistributorEntitlement> distributorEntitlementsList = distributorEntitlementRepository.findByDistributorId(distributorId);
		return getDistributorEntitlementResponse(distributorEntitlementsList);
		
	}

	@Override
	public WebProxyDistributorEntitlementResponse getRequestError(Exception e) {
		
		String errorMessage = e.getMessage();
		
		DistributorEntitlementResponse distributorEntitlementResponse = new DistributorEntitlementResponse();
		distributorEntitlementResponse.setErrorCode("1");
		distributorEntitlementResponse.setErrorMessage(errorMessage);
		
		WebProxyDistributorEntitlementResponse response = new WebProxyDistributorEntitlementResponse();
		response.setDistributorEntitlementResponse(distributorEntitlementResponse);
		
		return response;
		
	}
	
	private WebProxyDistributorEntitlementResponse getDistributorEntitlementResponse(List<DistributorEntitlement> distributorEntitlementsList) throws Exception {
		
		
		DistributorEntitlements distributorEntitlements = new DistributorEntitlements();
		distributorEntitlements.setDistributorEntitlementList(distributorEntitlementsList);
		
		DistributorEntitlementResponse distributorEntitlementResponse = new DistributorEntitlementResponse();
		distributorEntitlementResponse.setDistributorEntitlements(distributorEntitlements);
		
		WebProxyDistributorEntitlementResponse response = new WebProxyDistributorEntitlementResponse();
		response.setDistributorEntitlementResponse(distributorEntitlementResponse);
		
		return response;
		
	}

}
