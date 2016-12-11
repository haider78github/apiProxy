package com.softech.ls360.web.proxy.endpoint.model.response.entitlement.customer;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.softech.ls360.lms.proxy.entities.CustomerEntitlement;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CourseGroups;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.Courses;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CustomerEntitlementError;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.OrganizationalGroups;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "CustomerEntitlement", "OrganizationalGroups", "CourseGroups", "Courses" })
public class CustomerEntitlementDetailsResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value="CustomerEntitlement")
	protected CustomerEntitlement customerEntitlement;
	
	@JsonProperty(value="OrganizationalGroups")
	protected OrganizationalGroups organizationalGroups;
	
	@JsonProperty(value="CourseGroups")
	protected CourseGroups courseGroups;
	
	@JsonProperty(value="Courses")
	protected Courses courses;
	
	@JsonProperty(value="ResponseMessage")
	protected String message;
	
	private CustomerEntitlementError entitlementError;
	
	public CustomerEntitlement getCustomerEntitlement() {
		return customerEntitlement;
	}
	
	public void setCustomerEntitlement(CustomerEntitlement customerEntitlement) {
		this.customerEntitlement = customerEntitlement;
	}
	
	public OrganizationalGroups getOrganizationalGroups() {
		return organizationalGroups;
	}
	
	public void setOrganizationalGroups(OrganizationalGroups organizationalGroups) {
		this.organizationalGroups = organizationalGroups;
	}
	
	public CourseGroups getCourseGroups() {
		return courseGroups;
	}
	
	public void setCourseGroups(CourseGroups courseGroups) {
		this.courseGroups = courseGroups;
	}
	 
	public Courses getCourses() {
		return courses;
	}
	
	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CustomerEntitlementError getEntitlementError() {
		return entitlementError;
	}

	public void setEntitlementError(CustomerEntitlementError entitlementError) {
		this.entitlementError = entitlementError;
	}

}
