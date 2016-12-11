package com.softech.ls360.web.proxy.endpoint.model.response.coursegroup;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ChildCourseGroup {

	@JsonProperty(value="Guid")
	private List<String> childCourseGroupsGuidList;

	public List<String> getChildCourseGroupsGuidList() {
		return childCourseGroupsGuidList;
	}

	public void setChildCourseGroupsGuidList(List<String> childCourseGroupsGuidList) {
		this.childCourseGroupsGuidList = childCourseGroupsGuidList;
	}
	
	@Override
	public String toString(){
	    return "ChildCourseGroup [ChildCourseGroupsGuidList = "+childCourseGroupsGuidList+"]";
	}
	
}
