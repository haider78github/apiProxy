package com.softech.ls360.web.proxy.endpoint.model.request.coursegroup;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"customerEntitlementId", 
	"courseGroup"
})
public class CourseGroupDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value="CustomerEntitlementId")
	@XmlElement(name = "CustomerEntitlementId", required = true)
	protected Long customerEntitlementId;
	
	@JsonProperty(value="CourseGroup")
	@XmlElement(name = "CourseGroup", required = true)
	protected CourseGroup courseGroup;
	
	public CourseGroup getCourseGroup() {
		return courseGroup;
	}

	public void setCourseGroup(CourseGroup courseGroup) {
		this.courseGroup = courseGroup;
	}
	
	public Long getCustomerEntitlementId() {
		return customerEntitlementId;
	}

	public void setCustomerEntitlementId(Long customerEntitlementId) {
		this.customerEntitlementId = customerEntitlementId;
	}

	@Override
	public String toString() {
		return "CourseGroupDetailsRequest [courseGroup=" + courseGroup + "]";
	}
	
}
