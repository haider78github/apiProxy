package com.softech.ls360.web.proxy.endpoint.model.request.coursegroup;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "courseGroupDetailsRequest"
})
@XmlRootElement(name="json")
public class WebProxyCourseGroupDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value="CourseGroupDetailsRequest")
	@XmlElement(name = "CourseGroupDetailsRequest", required = true)
	private CourseGroupDetailsRequest courseGroupDetailsRequest;

	public CourseGroupDetailsRequest getCourseGroupDetailsRequest() {
		return courseGroupDetailsRequest;
	}

	public void setCourseGroupDetailsRequest(CourseGroupDetailsRequest courseGroupDetailsRequest) {
		this.courseGroupDetailsRequest = courseGroupDetailsRequest;
	}

	@Override
	public String toString() {
		return "WebProxyCourseGroupDetailsRequest [courseGroupDetailsRequest="
				+ courseGroupDetailsRequest + "]";
	}
	
}
