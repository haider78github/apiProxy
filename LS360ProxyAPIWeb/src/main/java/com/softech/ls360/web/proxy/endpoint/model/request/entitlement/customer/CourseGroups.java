package com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.softech.ls360.lms.proxy.entities.CourseGroup;

@JsonInclude(Include.NON_NULL)
//@JsonTypeName(value="CourseGroups")
public class CourseGroups implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(value="EntitlementCourseGroups")
	protected List<CourseGroup> customerEntitlementCourseGroupsList;

	public List<CourseGroup> getCustomerEntitlementCourseGroupsList() {
		return customerEntitlementCourseGroupsList;
	}

	public void setCustomerEntitlementCourseGroupsList(List<CourseGroup> customerEntitlementCourseGroupsList) {
		this.customerEntitlementCourseGroupsList = customerEntitlementCourseGroupsList;
	}
	
}
