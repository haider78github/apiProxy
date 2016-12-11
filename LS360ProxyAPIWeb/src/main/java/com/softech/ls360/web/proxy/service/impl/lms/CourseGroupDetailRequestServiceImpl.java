package com.softech.ls360.web.proxy.service.impl.lms;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.softech.ls360.lms.proxy.entities.Course;
import com.softech.ls360.lms.proxy.entities.CustomerEntitlement;
import com.softech.ls360.lms.proxy.repositories.CourseGroupRepository;
import com.softech.ls360.lms.proxy.repositories.CourseRepository;
import com.softech.ls360.lms.proxy.repositories.CustomerEntitlementRepository;
import com.softech.ls360.web.proxy.endpoint.model.request.coursegroup.CourseGroup;
import com.softech.ls360.web.proxy.endpoint.model.request.coursegroup.CourseGroupDetailsRequest;
import com.softech.ls360.web.proxy.endpoint.model.request.coursegroup.CourseGroupError;
import com.softech.ls360.web.proxy.endpoint.model.request.coursegroup.WebProxyCourseGroupDetailsRequest;
import com.softech.ls360.web.proxy.endpoint.model.response.coursegroup.ChildCourseGroup;
import com.softech.ls360.web.proxy.endpoint.model.response.coursegroup.CourseGroupDetailsResponse;
import com.softech.ls360.web.proxy.endpoint.model.response.coursegroup.WebProxyCourseGroupDetailsResponse;
import com.softech.ls360.web.proxy.service.lms.CourseGroupDetailRequestService;

@Service
public class CourseGroupDetailRequestServiceImpl implements CourseGroupDetailRequestService {

	@Inject
	private CourseGroupRepository courseGroupRepository;
	
	@Inject
	private CourseRepository courseRepository;

	@Inject
	private CustomerEntitlementRepository customerEntitlementRepository;

	@Override
	@Transactional
	public WebProxyCourseGroupDetailsResponse getCourseDetail(WebProxyCourseGroupDetailsRequest request) throws Exception {
		
		CourseGroupDetailsRequest courseGroupDetailsRequest = request.getCourseGroupDetailsRequest();
		CourseGroup courseGroup = courseGroupDetailsRequest.getCourseGroup();
		String courseGroupGuid =  courseGroup.getCourseGroupGuid();
		Long customerEntitlementId = courseGroupDetailsRequest.getCustomerEntitlementId();

		List<String> childCourseGroupsGuidList = null;
		List<com.softech.ls360.web.proxy.endpoint.model.response.coursegroup.Course> courseGroupCoursesList = null;
		List<com.softech.ls360.lms.proxy.entities.CourseGroup> immeditateChildCourseGroups = null;

		com.softech.ls360.lms.proxy.entities.CourseGroup dbCourseGroup = null;
		List<Course> courseGroupCourses = null;

		CustomerEntitlement ce = customerEntitlementRepository.findOne(customerEntitlementId);

		if(ce!=null && ce.getEnrollmentType().equals("CourseGroup")){

			dbCourseGroup = courseGroupRepository.findByCourseGroupGuidAndCourseGroupCustomerEntitlementCustomerEntitlementId(courseGroupGuid, customerEntitlementId);

			if(dbCourseGroup!=null){
			
				immeditateChildCourseGroups = (List<com.softech.ls360.lms.proxy.entities.CourseGroup>) dbCourseGroup.getParentCourseGroup();
				if (!CollectionUtils.isEmpty(immeditateChildCourseGroups)) {
					childCourseGroupsGuidList = immeditateChildCourseGroups.stream()
							.map(com.softech.ls360.lms.proxy.entities.CourseGroup::getCourseGroupGuid)
							.collect(Collectors.toList());
				}
				
				courseGroupCourses = courseRepository.findByCourseCourseGroupCourseGroupCourseGroupGuidAndCourseStatus(courseGroupGuid, "Published");
				if (!CollectionUtils.isEmpty(courseGroupCourses)) {
					courseGroupCoursesList = courseGroupCourses.stream()
							.map(p ->  {
								com.softech.ls360.web.proxy.endpoint.model.response.coursegroup.Course courseGroupCourse = new com.softech.ls360.web.proxy.endpoint.model.response.coursegroup.Course();
								courseGroupCourse.setName(p.getName());
								courseGroupCourse.setGuid(p.getCourseGuid());
								return courseGroupCourse;
							})
							.collect(Collectors.toList());
				}
			}else{
				throw new Exception("Invalid CourseGroup GUID / Entitlement ID - not found in DB");
			}
		} else {
			throw new Exception("Entitlement not found or Entitlement is not of CourseGroup type");
		}
			
		return getCourseGroupDetailsResponse(dbCourseGroup, childCourseGroupsGuidList, courseGroupCoursesList);
	}
	
	private WebProxyCourseGroupDetailsResponse getCourseGroupDetailsResponse(com.softech.ls360.lms.proxy.entities.CourseGroup dbCourseGroup, 
			List<String> childCourseGroupsGuidList, List<com.softech.ls360.web.proxy.endpoint.model.response.coursegroup.Course> courseGroupCoursesList) {
		
		com.softech.ls360.web.proxy.endpoint.model.response.coursegroup.CourseGroup respCourseGroup = new com.softech.ls360.web.proxy.endpoint.model.response.coursegroup.CourseGroup();
		if (dbCourseGroup != null) {
			respCourseGroup.setName(dbCourseGroup.getName());
			respCourseGroup.setGuid(dbCourseGroup.getCourseGroupGuid());
			//respCourseGroup.setParentId(String.valueOf(dbCourseGroup.getParentCourseGroupId()));
		}
		
		if (!CollectionUtils.isEmpty(childCourseGroupsGuidList)) {
			ChildCourseGroup childCourseGroup = new ChildCourseGroup();
			childCourseGroup.setChildCourseGroupsGuidList(childCourseGroupsGuidList);
			respCourseGroup.setChildCourseGroup(childCourseGroup);
		}
		
		if (!CollectionUtils.isEmpty(courseGroupCoursesList)) {
			respCourseGroup.setCourses(courseGroupCoursesList);
		}
		
		CourseGroupDetailsResponse courseGroupDetailsResponse = new CourseGroupDetailsResponse();
		courseGroupDetailsResponse.setCourseGroup(respCourseGroup);
		
		WebProxyCourseGroupDetailsResponse response = new WebProxyCourseGroupDetailsResponse();
		response.setCourseGroupDetailsResponse(courseGroupDetailsResponse);
		return response;
		
	}
	
	@Override
	public WebProxyCourseGroupDetailsResponse getRequestError(Exception e) {
		String errorMessage = e.getMessage();
		
		CourseGroupError courseGroupError = new CourseGroupError();
		courseGroupError.setErrorCode("1");
		courseGroupError.setErrorMessage(errorMessage);
		
		CourseGroupDetailsResponse courseGroupDetailsResponse = new CourseGroupDetailsResponse();
		courseGroupDetailsResponse.setCourseGroupError(courseGroupError);
		
		WebProxyCourseGroupDetailsResponse response = new WebProxyCourseGroupDetailsResponse();
		response.setCourseGroupDetailsResponse(courseGroupDetailsResponse);
		
		return response;
	}

}
