package com.softech.ls360.web.proxy.service.lms;

import java.util.List;

import com.softech.ls360.lms.proxy.entities.LearnerCourseStatistics1;
import com.softech.ls360.web.proxy.endpoint.model.request.lcs.CourseStatsUpdateRequest;
import com.softech.ls360.web.proxy.endpoint.model.response.lcs.CourseCompletionStatsUpdateResponse;

public interface LearnerCourseStatisticsService {

	CourseCompletionStatsUpdateResponse updateLearnerCourseStatistics(CourseStatsUpdateRequest courseCompletionStatsUpdateRequest) throws Exception;
	List<LearnerCourseStatistics1> getLearnerCourseStatistics(String distributorId, String fromDate, String toDate) throws Exception;
	
}
