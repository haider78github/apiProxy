package com.softech.ls360.web.proxy.endpoint.model.response.coursegroup;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.softech.ls360.web.proxy.endpoint.model.request.coursegroup.CourseGroupError;

@JsonInclude(Include.NON_NULL)
public class CourseGroupDetailsResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value="CourseGroup")
	private CourseGroup courseGroup;
	
	private CourseGroupError courseGroupError;

	public CourseGroup getCourseGroup() {
		return courseGroup;
	}

	public void setCourseGroup(CourseGroup courseGroup) {
		this.courseGroup = courseGroup;
	}
	
	public CourseGroupError getCourseGroupError() {
		return courseGroupError;
	}

	public void setCourseGroupError(CourseGroupError courseGroupError) {
		this.courseGroupError = courseGroupError;
	}

	@Override
	public String toString() {
		return "CourseGroupDetailsResponse [courseGroup=" + courseGroup + "]";
	}

}
