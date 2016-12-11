package com.softech.ls360.web.proxy.endpoint.model.request.coursegroup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CourseGroup", propOrder = {
    "courseGroupGuid"
})
public class CourseGroup {

	@JsonProperty(value="Guid")
	@XmlElement(name = "Guid", required = true)
	private String courseGroupGuid;

	public String getCourseGroupGuid() {
		return courseGroupGuid;
	}

	public void setCourseGroupGuid(String courseGroupGuid) {
		this.courseGroupGuid = courseGroupGuid;
	}

	@Override
	public String toString() {
		return "CourseGroup [courseGroupGuid=" + courseGroupGuid + "]";
	}
	
}
