package com.softech.ls360.web.proxy.endpoint.model.response.lcs;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.softech.ls360.lms.proxy.entities.CourseCompletionStatsUpdate;

public class CourseCompletionStatsUpdateResponse {

	private List<CourseCompletionStatsUpdate> courseCompletionStatsUpdateResponseList;
	//private Map<CourseCompletionStatsUpdate, String> courseCompletionsStatsUpdateResponseErrorMap;
	
	@JsonProperty("CourseStatusUpdateResponse")
	public List<CourseCompletionStatsUpdate> getCourseCompletionStatsUpdateResponseList() {
		return courseCompletionStatsUpdateResponseList;
	}
	public void setCourseCompletionStatsUpdateResponseList(List<CourseCompletionStatsUpdate> courseCompletionStatsUpdateResponseList) {
		this.courseCompletionStatsUpdateResponseList = courseCompletionStatsUpdateResponseList;
	}
	
	/**
	 * 
	 * @return
	
	public Map<CourseCompletionStatsUpdate, String> getCourseCompletionsStatsUpdateResponseErrorMap() {
		return courseCompletionsStatsUpdateResponseErrorMap;
	}
	
	public void setCourseCompletionsStatsUpdateResponseErrorMap(Map<CourseCompletionStatsUpdate, String> courseCompletionsStatsUpdateResponseErrorMap) {
		this.courseCompletionsStatsUpdateResponseErrorMap = courseCompletionsStatsUpdateResponseErrorMap;
	}
	 */
}
