package com.softech.ls360.web.proxy.service.impl.lms;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.softech.ls360.lms.proxy.entities.Course;
import com.softech.ls360.lms.proxy.entities.CourseGroup;
import com.softech.ls360.lms.proxy.entities.OrganizationalGroupEntitlement;
import com.softech.ls360.lms.proxy.repositories.CourseGroupRepository;
import com.softech.ls360.lms.proxy.repositories.CourseRepository;
import com.softech.ls360.lms.proxy.repositories.CustomerEntitlementRepository;
import com.softech.ls360.lms.proxy.repositories.OrganizationalGroupEntitlementRepository;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CourseGroups;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.Courses;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CustomerEntitlement;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CustomerEntitlementDetailsRequest;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CustomerEntitlementError;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.OrganizationalGroups;
import com.softech.ls360.web.proxy.endpoint.model.response.entitlement.customer.CustomerEntitlementDetailsResponse;
import com.softech.ls360.web.proxy.service.MessageService;
import com.softech.ls360.web.proxy.service.lms.CustomerEntitlementDetailsRequestService;

@Service
public class CustomerEntitlementDetailsRequestServiceImpl implements CustomerEntitlementDetailsRequestService {

	@Inject
	private CustomerEntitlementRepository  customerEntitlementRepository;
	
	@Inject
	private OrganizationalGroupEntitlementRepository  organizationalGroupEntitlementRepository;
	
	@Inject
	private CourseGroupRepository courseGroupRepository;
	
	@Inject
	private CourseRepository courseRepository;
	
	@Inject
	private MessageService messageService;
	
	@Override
	public CustomerEntitlementDetailsResponse getCustomerEntitlementDetails( CustomerEntitlementDetailsRequest request) throws Exception {
		CustomerEntitlement reqCustomerEntitlement = request.getCustomerEntitlement();
		Long reqCustomerEntitlementId = reqCustomerEntitlement.getEntitlementId();
		com.softech.ls360.lms.proxy.entities.CustomerEntitlement customerEntitlement = customerEntitlementRepository.findOne(reqCustomerEntitlementId);
		if (customerEntitlement == null) {
			return getCustomerEntitlementDetailsResponse(reqCustomerEntitlementId);
		}
		Long customerEntitlementId  = customerEntitlement.getId();
		List<OrganizationalGroupEntitlement> organizationalGroupEntitlementList = organizationalGroupEntitlementRepository.findByCustomerEntitlementId(customerEntitlementId);
		List<Course> customerEntitlementCourseList = null;
		List<CourseGroup> customerEntitlementCourseGroupsList = null;
		String contractType = customerEntitlement.getEnrollmentType();
		if (StringUtils.isNotBlank(contractType)) {
			contractType = contractType.toLowerCase();
			switch (contractType) {
			    case "course":
			    	customerEntitlementCourseList = courseRepository.findByCourseCustomerEntitlementCustomerEntitlementId(customerEntitlementId);
			    	break;
			    case "coursegroup":
			    	customerEntitlementCourseGroupsList = courseGroupRepository.findByCourseGroupCustomerEntitlementCustomerEntitlementId(customerEntitlementId);
			    	break;
			}
		}
		
		CustomerEntitlementDetailsResponse response = getCustomerEntitlementDetailsResponse(customerEntitlement, organizationalGroupEntitlementList, customerEntitlementCourseList, customerEntitlementCourseGroupsList);
		return response;
	}

	@Override
	public CustomerEntitlementDetailsResponse getEntitlementErrorResponse(Exception e) {
		
		String errorMessage = e.getMessage();
		CustomerEntitlementError entitlementError = new CustomerEntitlementError();
		entitlementError.setErrorCode("1");
		entitlementError.setErrorMessage(errorMessage);
		CustomerEntitlementDetailsResponse customerEntitlementDetailsResponse = new CustomerEntitlementDetailsResponse();
		customerEntitlementDetailsResponse.setEntitlementError(entitlementError);
		return customerEntitlementDetailsResponse;
		
	}
	
	private CustomerEntitlementDetailsResponse getCustomerEntitlementDetailsResponse(com.softech.ls360.lms.proxy.entities.CustomerEntitlement customerEntitlement, 
			List<OrganizationalGroupEntitlement> organizationalGroupEntitlementList,  List<Course> customerEntitlementCourseList, 
			List<CourseGroup> customerEntitlementCourseGroupsList) {
		
		CustomerEntitlementDetailsResponse response = new CustomerEntitlementDetailsResponse();
		
		response.setCustomerEntitlement(customerEntitlement);
		if (!CollectionUtils.isEmpty(organizationalGroupEntitlementList)) {
			OrganizationalGroups organizationalGroups = new OrganizationalGroups();
			organizationalGroups.setOrganizationalGroupEntitlementList(organizationalGroupEntitlementList);
			response.setOrganizationalGroups(organizationalGroups);
		}
		
		if (!CollectionUtils.isEmpty(customerEntitlementCourseGroupsList)) {
			CourseGroups courseGroups = new CourseGroups();
			courseGroups.setCustomerEntitlementCourseGroupsList(customerEntitlementCourseGroupsList);
			response.setCourseGroups(courseGroups);
		}
		
		if (!CollectionUtils.isEmpty(customerEntitlementCourseList)) {
			Courses courses = new Courses();
			courses.setEntitlementCoursesList(customerEntitlementCourseList);
			response.setCourses(courses);
		}
		
		return response;
		
	}
	
	private CustomerEntitlementDetailsResponse getCustomerEntitlementDetailsResponse(Long reqCustomerEntitlementId) {
		
		String message = messageService.getLocalizeMessage("message.entitlements.customer.none");
		
		CustomerEntitlementDetailsResponse response = new CustomerEntitlementDetailsResponse();
		response.setMessage(message + " id: " + reqCustomerEntitlementId);
		return response;
		
	}

}
