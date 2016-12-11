package com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.softech.ls360.lms.proxy.entities.OrganizationalGroupEntitlement;

@JsonInclude(Include.NON_NULL)
//@JsonTypeName(value="OrganizationalGroups")
public class OrganizationalGroups implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value="OrganizationalGroupEntitlements")
	protected List<OrganizationalGroupEntitlement> organizationalGroupEntitlementList;

	public List<OrganizationalGroupEntitlement> getOrganizationalGroupEntitlementList() {
		return organizationalGroupEntitlementList;
	}

	public void setOrganizationalGroupEntitlementList(List<OrganizationalGroupEntitlement> organizationalGroupEntitlementList) {
		this.organizationalGroupEntitlementList = organizationalGroupEntitlementList;
	}
	
}
