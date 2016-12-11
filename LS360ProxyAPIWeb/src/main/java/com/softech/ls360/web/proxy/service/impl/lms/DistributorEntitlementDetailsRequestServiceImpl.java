package com.softech.ls360.web.proxy.service.impl.lms;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.softech.ls360.lms.proxy.entities.CourseGroup;
import com.softech.ls360.lms.proxy.repositories.CourseGroupRepository;
import com.softech.ls360.lms.proxy.repositories.DistributorEntitlementRepository;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor.DistributorEntitlement;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor.DistributorEntitlementDetailsRequest;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor.WebProxyDistributorEntitlementDetailsRequest;
import com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor.CourseGroups;
import com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor.DistributorEntitlementDetailsResponse;
import com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor.WebProxyDistributorEntitlementDetailsResponse;
import com.softech.ls360.web.proxy.service.lms.DistributorEntitlementDetailsRequestService;

@Service
public class DistributorEntitlementDetailsRequestServiceImpl implements DistributorEntitlementDetailsRequestService {

	@Inject
	private DistributorEntitlementRepository distributorEntitlementRepository;
	
	@Inject
	private CourseGroupRepository courseGroupRepository;
	
	@Override
	public WebProxyDistributorEntitlementDetailsResponse getDistributorEntitlementDetails(WebProxyDistributorEntitlementDetailsRequest request) throws Exception {
		
		DistributorEntitlementDetailsRequest distributorEntitlementDetailsRequest = request.getDistributorEntitlementDetailsRequest();
		DistributorEntitlement reqDistributorEntitlement = distributorEntitlementDetailsRequest.getDistributorEntitlement();
		Long distributorEntitlementId = reqDistributorEntitlement.getId();
		com.softech.ls360.lms.proxy.entities.DistributorEntitlement dbDistributorEntitlement = distributorEntitlementRepository.findOne(distributorEntitlementId);
		List<CourseGroup> distributorEntitlementCourseGroupsList = courseGroupRepository.findByDistributorEntitlementId(distributorEntitlementId);
		return getDistributorEntitlementDetailsResponse(dbDistributorEntitlement, distributorEntitlementCourseGroupsList);
		
	}

	@Override
	public WebProxyDistributorEntitlementDetailsResponse getRequestError(Exception e) {
		
		String errorMessage = e.getMessage();
		
		DistributorEntitlementDetailsResponse distributorEntitlementDetailsResponse = new DistributorEntitlementDetailsResponse();
		distributorEntitlementDetailsResponse.setErrorCode("1");
		distributorEntitlementDetailsResponse.setErrorMessage(errorMessage);
		
		WebProxyDistributorEntitlementDetailsResponse response = new WebProxyDistributorEntitlementDetailsResponse();
		response.setDistributorEntitlementDetailsResponse(distributorEntitlementDetailsResponse);
		
		return response;
	}
	
	private WebProxyDistributorEntitlementDetailsResponse getDistributorEntitlementDetailsResponse(com.softech.ls360.lms.proxy.entities.DistributorEntitlement dbDistributorEntitlement,
			List<CourseGroup> distributorEntitlementCourseGroupsList) throws Exception {
		
		CourseGroups respDistributorEntitlementCourseGroups = new CourseGroups();
		respDistributorEntitlementCourseGroups.setDistributorEntitlementCourseGroupsList(distributorEntitlementCourseGroupsList);
		
		com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor.DistributorEntitlement respDistributorEntitlement = new com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor.DistributorEntitlement();
		
		if (dbDistributorEntitlement != null) {
			respDistributorEntitlement.setId(dbDistributorEntitlement.getId());
			respDistributorEntitlement.setName(dbDistributorEntitlement.getName());
			respDistributorEntitlement.setSeats(dbDistributorEntitlement.getSeats());
			respDistributorEntitlement.setAllowSelfEnrollmentTf(dbDistributorEntitlement.getAllowSelfEnrollmentTf());
			respDistributorEntitlement.setAllowUnlimitedEnrollments(dbDistributorEntitlement.getAllowUnlimitedEnrollments());
			respDistributorEntitlement.setStartDate(dbDistributorEntitlement.getStartDate());
			respDistributorEntitlement.setEndDate(dbDistributorEntitlement.getEndDate());
			respDistributorEntitlement.setNumberDays(dbDistributorEntitlement.getNumberDays());
			respDistributorEntitlement.setNumberSeatsUsed(dbDistributorEntitlement.getNumberSeatsUsed());
			respDistributorEntitlement.setDistributorEntitlementCourseGroups(respDistributorEntitlementCourseGroups);
		}
		
		DistributorEntitlementDetailsResponse  distributorEntitlementDetailsResponse = new DistributorEntitlementDetailsResponse();
		distributorEntitlementDetailsResponse.setRespDistributorEntitlement(respDistributorEntitlement);
		
		WebProxyDistributorEntitlementDetailsResponse response = new WebProxyDistributorEntitlementDetailsResponse();
		response.setDistributorEntitlementDetailsResponse(distributorEntitlementDetailsResponse);
		
		return response;
		
	}

}
