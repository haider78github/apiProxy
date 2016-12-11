package com.softech.ls360.web.proxy.endpoint.model.response.enrollment;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.softech.ls360.lms.proxy.entities.Enrollments;

public class ResellerEnrollments implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Enrollments> resellerEnrollmentsList;

	public ResellerEnrollments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResellerEnrollments(List<Enrollments> resellerEnrollmentsList) {
		super();
		this.resellerEnrollmentsList = resellerEnrollmentsList;
	}

	@JsonProperty("Enrollments")
	public List<Enrollments> getResellerEnrollmentsList() {
		return resellerEnrollmentsList;
	}

	public void setResellerEnrollmentsList(List<Enrollments> resellerEnrollmentsList) {
		this.resellerEnrollmentsList = resellerEnrollmentsList;
	}
	
}
