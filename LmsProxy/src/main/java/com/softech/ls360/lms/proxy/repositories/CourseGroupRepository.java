package com.softech.ls360.lms.proxy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.softech.ls360.lms.proxy.entities.CourseGroup;

public interface CourseGroupRepository extends CrudRepository<CourseGroup, Long> {

	List<CourseGroup> findByCourseGroupCustomerEntitlementCustomerEntitlementId(Long customerEntitlementId);
	List<CourseGroup> findByDistributorEntitlementId(Long distributorEntitlementId);
	CourseGroup findByCourseGroupGuid(String courseGroupGuid);
	
	//@Query("select cg from #{#entityName} cg "
    		//+ "join fetch cg.parentCourseGroup cg1 "
    		//+ "where cg1.id=:cg.id")
	//List<CourseGroup> findImmediateChildCourseGruopsByCourseGroupGuid(@Param("courseGroupGuid") String courseGroupGuid);
	//LMS-21546
	List<CourseGroup> findByParentCourseGroupId(Long id);

	List<CourseGroup> findByIdAndCourseCourseGroup_Course_CourseStatusIgnoreCaseAndCourseCourseGroup_CourseRetiredTfFalse(Long id, String status);
    
    CourseGroup findByCourseGroupGuidAndCourseGroupCustomerEntitlementCustomerEntitlementId(String courseGroupGuid, Long customerEntitlementId);
	

}
