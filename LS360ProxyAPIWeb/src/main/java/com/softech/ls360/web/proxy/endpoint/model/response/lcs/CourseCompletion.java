package com.softech.ls360.web.proxy.endpoint.model.response.lcs;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.softech.ls360.lms.proxy.entities.LearnerCourseStatistics1;

//@JsonSerialize(using = CourseCompletionSerializer.class)
public class CourseCompletion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<LearnerCourseStatistics1> learnerCourseStatisticsList;

	public CourseCompletion() {
		super();
	}

	public CourseCompletion(List<LearnerCourseStatistics1> learnerCourseStatisticsList) {
		super();
		this.learnerCourseStatisticsList = learnerCourseStatisticsList;
	}
	
	// Jackson 2.x provides @JsonProperty to set property names during serialization/deserialization of JSON string.
	@JsonProperty("LearnerCourseCompletion")
	public List<LearnerCourseStatistics1> getLearnerCourseStatisticsList() {
		return learnerCourseStatisticsList;
	}

	public void setLearnerCourseStatisticsList(List<LearnerCourseStatistics1> learnerCourseStatisticsList) {
		this.learnerCourseStatisticsList = learnerCourseStatisticsList;
	}
	
}
