package com.softech.ls360.lms.proxy.test.repositories;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import com.softech.ls360.lms.proxy.entities.CourseGroup;
import com.softech.ls360.lms.proxy.repositories.CourseGroupRepository;
import com.softech.ls360.lms.proxy.test.LmsProxyAbstractTest;

public class CourseGroupRepositoryTest extends LmsProxyAbstractTest {
	
	@Inject
	private CourseGroupRepository courseGroupRepository;
	
	@Test
	public void test1() {
		
	}
	
	//@Test
	public void findImmediateChildCourseGroups() {
		
		String courseGroupGuid = "76828592e02648ada53c8aa3b580d20e";
		try {
			
			List<com.softech.ls360.lms.proxy.entities.CourseGroup> immediateChildCourseGroups = courseGroupRepository.findImmediateChildCourseGruopsByCourseGroupGuid(courseGroupGuid);
			if (!CollectionUtils.isEmpty(immediateChildCourseGroups)) {
				List<String> childCourseGroupsGuidList = immediateChildCourseGroups.stream()
						.map(com.softech.ls360.lms.proxy.entities.CourseGroup::getCourseGroupGuid)
						.collect(Collectors.toList());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void findCustomerEntitlementCourseGroups() {
		
		Long customerEntitlementId = 101L;
		
		try {
			List<CourseGroup> customerEntitlementCourseGroupsList = courseGroupRepository.findByCourseGroupCustomerEntitlementCustomerEntitlementId(customerEntitlementId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//@Test
	public void findDistributorEntitlementCourseGroups() {
		
		Long distributorEntitlementId = 601L;  
		try {
			List<CourseGroup> courseGroupList = courseGroupRepository.findByDistributorEntitlementId(distributorEntitlementId);
			System.out.println(courseGroupList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void findByIdAndCourseCourseGroupCourseCourseStatusIgnoreCaseAndCourseCourseGroupCourseRetiredTfFalse() {
		
		Long courseGroupId = 8L;  
		try {
			List<CourseGroup> courseGroupList = courseGroupRepository.findByIdAndCourseCourseGroup_Course_CourseStatusIgnoreCaseAndCourseCourseGroup_CourseRetiredTfFalse(courseGroupId, "Published");
			System.out.println(courseGroupList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			
}
