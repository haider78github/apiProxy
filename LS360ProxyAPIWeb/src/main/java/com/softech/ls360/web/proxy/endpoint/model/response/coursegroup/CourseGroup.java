package com.softech.ls360.web.proxy.endpoint.model.response.coursegroup;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CourseGroup {

	@JsonProperty(value="Name")
	private String name;
	
	@JsonProperty(value="Guid")
	private String guid;
	
	@JsonProperty(value="ParentId")
    private String parentId;

    @JsonProperty(value="ChildCourseGroup")
	private ChildCourseGroup childCourseGroup;

    //@JsonProperty(value="Courses")
	//private Courses courses;
    
    @JsonProperty(value="Courses")
    private List<Course> courses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public ChildCourseGroup getChildCourseGroup() {
		return childCourseGroup;
	}

	public void setChildCourseGroup(ChildCourseGroup childCourseGroup) {
		this.childCourseGroup = childCourseGroup;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	//public Courses getCourses() {
		//return courses;
	//}

	//public void setCourses(Courses courses) {
		//this.courses = courses;
	//}

	//@Override
	//public String toString() {
		//return "CourseGroup [name=" + name + ", guid=" + guid + ", parentId="
				//+ parentId + ", childCourseGroup=" + childCourseGroup
				//+ ", courses=" + courses + "]";
	//}
	
}
