package com.softech.ls360.lms.proxy.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.softech.ls360.lms.proxy.entities.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

	Course findByCourseGuid(String courseGuid);
	List<Course> findByCourseCustomerEntitlementCustomerEntitlementId(Long customerEntitlementId);
	List<Course> findByCourseCourseGroupCourseGroupCourseGroupGuid(String courseGroupGuid);
	List<Course> findByCourseCourseGroupCourseGroupCourseGroupGuidAndCourseStatus(String courseGroupGuid, String courseStatus);
	
	List<Course> findByCourseCustomerEntitlementCustomerEntitlementIdAndCourseCustomerEntitlementCourseGroupCourseGroupGuid(Long customerEntitlementId, String courseGroupGuid);
	List<Course> findByCourseCustomerEntitlementCourseGroupCourseGroupGuid(String courseGroupGuid);
	
}
