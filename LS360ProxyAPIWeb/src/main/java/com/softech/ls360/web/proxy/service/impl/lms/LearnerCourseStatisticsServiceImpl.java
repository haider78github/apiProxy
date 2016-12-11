package com.softech.ls360.web.proxy.service.impl.lms;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.softech.ls360.lms.proxy.entities.CourseCompletionStatsUpdate;
import com.softech.ls360.lms.proxy.entities.LearnerCourseStatistics1;
import com.softech.ls360.lms.proxy.repositories.LearnerCourseStatisticsRepository;
import com.softech.ls360.web.proxy.endpoint.model.request.lcs.CourseStatsUpdateRequest;
import com.softech.ls360.web.proxy.endpoint.model.response.lcs.CourseCompletionStatsUpdateResponse;
import com.softech.ls360.web.proxy.service.lms.LearnerCourseStatisticsService;


@Service 
public class LearnerCourseStatisticsServiceImpl implements LearnerCourseStatisticsService {

	@Inject 
	private LearnerCourseStatisticsRepository learnerCourseStatisticsRepository;
	
	@Override
	public CourseCompletionStatsUpdateResponse updateLearnerCourseStatistics(CourseStatsUpdateRequest courseCompletionStatsUpdateRequest) throws Exception {
		
		CourseCompletionStatsUpdateResponse courseCompletionStatsUpdateResponse = new CourseCompletionStatsUpdateResponse();
		
		List<CourseCompletionStatsUpdate> courseCompletionStatsUpdateResponseList = new ArrayList<CourseCompletionStatsUpdate>();
		List<CourseCompletionStatsUpdate> courseCompletionStatsUpdateSuccessfulList = new ArrayList<CourseCompletionStatsUpdate>();
		
		List<CourseCompletionStatsUpdate> courseCompletionStatsUpdateList = courseCompletionStatsUpdateRequest.getCourseStatsUpdate();
		List<CourseCompletionStatsUpdate> courseCompletionStatsUpdateRequestErrorList = courseCompletionStatsUpdateRequest.getCourseCompletionStatsUpdateRequestErrorList();
		
		for (CourseCompletionStatsUpdate courseStats : courseCompletionStatsUpdateList) {
			try {
				
				learnerCourseStatisticsRepository.updateLearnerCourseStatistics(courseStats);
				courseStats.setErrorCode("0");
				courseStats.setErrorMessage("");
				courseCompletionStatsUpdateSuccessfulList.add(courseStats);
			} catch (Exception e) {
				String errorMessage = e.getMessage();
				courseStats.setErrorCode("1");
				courseStats.setErrorMessage(errorMessage);
				courseCompletionStatsUpdateRequestErrorList.add(courseStats);
			}
		}
		
		courseCompletionStatsUpdateResponseList.addAll(courseCompletionStatsUpdateRequestErrorList);
		courseCompletionStatsUpdateResponseList.addAll(courseCompletionStatsUpdateSuccessfulList);
		courseCompletionStatsUpdateResponse.setCourseCompletionStatsUpdateResponseList(courseCompletionStatsUpdateResponseList);
		return courseCompletionStatsUpdateResponse;
	}

	@Override
	public List<LearnerCourseStatistics1> getLearnerCourseStatistics(String distributorId, String fromDate, String toDate) throws Exception {
		return learnerCourseStatisticsRepository.getLearnerCourseStatistics(distributorId, fromDate, toDate);
	}
	
}
