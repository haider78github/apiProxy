package com.softech.ls360.lms.proxy.dao;

import java.util.List;

import com.softech.ls360.lms.proxy.entities.Enrollments;

public interface LearnerEnrollmentsDao {

	List<Enrollments> findEnrollmentByDistributorId(Long distributorId, String enrollmentStartDate, String enrollmentEndDate) throws Exception;
	List<Enrollments> findEnrollmentsByUserNameAndCoursesGuid(String userName, List<String> coursesGuid) throws Exception;
	
}
