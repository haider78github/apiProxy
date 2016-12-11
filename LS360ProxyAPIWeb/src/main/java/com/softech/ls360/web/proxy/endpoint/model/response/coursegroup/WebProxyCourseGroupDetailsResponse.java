package com.softech.ls360.web.proxy.endpoint.model.response.coursegroup;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebProxyCourseGroupDetailsResponse {

	@JsonProperty(value="CourseGroupDetailsResponse")
	private CourseGroupDetailsResponse courseGroupDetailsResponse;

	public CourseGroupDetailsResponse getCourseGroupDetailsResponse() {
		return courseGroupDetailsResponse;
	}

	public void setCourseGroupDetailsResponse(CourseGroupDetailsResponse courseGroupDetailsResponse) {
		this.courseGroupDetailsResponse = courseGroupDetailsResponse;
	}

	@Override
	public String toString() {
		return "WebProxyCourseGroupDetailsResponse [courseGroupDetailsResponse="
				+ courseGroupDetailsResponse + "]";
	}
	
}
