package com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.softech.ls360.lms.proxy.entities.CustomerEntitlement;

@JsonInclude(Include.NON_NULL)
public class CustomerEntitlements implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected List<CustomerEntitlement> customerEntitlementsList;
	
	@JsonProperty(value="ResponseMessage")
	protected String message;

	public List<CustomerEntitlement> getCustomerEntitlementsList() {
		return customerEntitlementsList;
	}

	public void setCustomerEntitlementsList(List<CustomerEntitlement> customerEntitlementsList) {
		this.customerEntitlementsList = customerEntitlementsList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
		
}
