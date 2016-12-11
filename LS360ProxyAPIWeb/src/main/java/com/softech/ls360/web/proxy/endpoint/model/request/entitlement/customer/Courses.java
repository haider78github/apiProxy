package com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.softech.ls360.lms.proxy.entities.Course;

@JsonInclude(Include.NON_NULL)
//@JsonTypeName(value="Courses")
public class Courses implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(value="EntitlementCourses")
	protected List<Course> entitlementCoursesList;

	public List<Course> getEntitlementCoursesList() {
		return entitlementCoursesList;
	}

	public void setEntitlementCoursesList(List<Course> entitlementCoursesList) {
		this.entitlementCoursesList = entitlementCoursesList;
	}
	
}
