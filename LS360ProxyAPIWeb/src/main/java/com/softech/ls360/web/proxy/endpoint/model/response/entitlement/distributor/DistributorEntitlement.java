package com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DistributorEntitlement", propOrder = {
    "distributorEntitlementCourseGroups"
})
@JsonInclude(Include.NON_NULL)
public class DistributorEntitlement extends com.softech.ls360.lms.proxy.entities.DistributorEntitlement {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value="CourseGroups")
	@XmlElement(name = "CourseGroups")
	private CourseGroups distributorEntitlementCourseGroups;
	
	public CourseGroups getDistributorEntitlementCourseGroups() {
		return distributorEntitlementCourseGroups;
	}

	public void setDistributorEntitlementCourseGroups(CourseGroups distributorEntitlementCourseGroups) {
		this.distributorEntitlementCourseGroups = distributorEntitlementCourseGroups;
	}

	@Override
	public String toString() {
		return "DistributorEntitlement [distributorEntitlementCourseGroups="
				+ distributorEntitlementCourseGroups + ", id=" + id + ", name="
				+ name + ", seats=" + seats + ", allowSelfEnrollmentTf="
				+ allowSelfEnrollmentTf + ", allowUnlimitedEnrollments="
				+ allowUnlimitedEnrollments + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", numberDays=" + numberDays
				+ ", numberSeatsUsed=" + numberSeatsUsed + "]";
	}
	
}
