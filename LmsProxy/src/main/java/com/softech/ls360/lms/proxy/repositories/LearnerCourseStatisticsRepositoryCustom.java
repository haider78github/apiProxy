package com.softech.ls360.lms.proxy.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.softech.ls360.lms.proxy.entities.CourseCompletionStatsUpdate;
import com.softech.ls360.lms.proxy.entities.LearnerCourseStatistics1;

public interface LearnerCourseStatisticsRepositoryCustom {

	@Transactional
	List<LearnerCourseStatistics1> getLearnerCourseStatistics(String distributorId, String fromDate, String toDate) throws Exception;
	
	@Transactional
	void updateLearnerCourseStatistics(CourseCompletionStatsUpdate courseStats) throws Exception;
	
}
