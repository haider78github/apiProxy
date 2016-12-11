package com.softech.ls360.web.proxy.endpoint.model.response.entitlement.customer;

import java.io.Serializable;

import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CustomerEntitlementError;
import com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer.CustomerEntitlements;

public class CustomerEntitlementResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected CustomerEntitlements customerEntitlements;
	
	private CustomerEntitlementError entitlementError;
	
	public CustomerEntitlements getCustomerEntitlements() {
		return customerEntitlements;
	}
	
	public void setCustomerEntitlements(CustomerEntitlements customerEntitlements) {
		this.customerEntitlements = customerEntitlements;
	}

	public CustomerEntitlementError getEntitlementError() {
		return entitlementError;
	}

	public void setEntitlementError(CustomerEntitlementError entitlementError) {
		this.entitlementError = entitlementError;
	}
	
}
