package com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.softech.ls360.lms.proxy.entities.CourseGroup;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CourseGroups", propOrder = {
    "distributorEntitlementCourseGroupsList"
})
@JsonInclude(Include.NON_NULL)
public class CourseGroups {

	@JsonProperty(value="CourseGroup")
	@XmlElement(name = "CourseGroup")
	private List<CourseGroup> distributorEntitlementCourseGroupsList;

	public List<CourseGroup> getDistributorEntitlementCourseGroupsList() {
		return distributorEntitlementCourseGroupsList;
	}

	public void setDistributorEntitlementCourseGroupsList(List<CourseGroup> distributorEntitlementCourseGroupsList) {
		this.distributorEntitlementCourseGroupsList = distributorEntitlementCourseGroupsList;
	}

	@Override
	public String toString() {
		return "CourseGroups [distributorEntitlementCourseGroupsList="
				+ distributorEntitlementCourseGroupsList + "]";
	}
	
}
