package com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "customerEntitlement"
})
@XmlRootElement(name = "CustomerEntitlementDetailsRequest")
public class CustomerEntitlementDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value="CustomerEntitlement")
	@XmlElement(name = "CustomerEntitlement", required = true)
	private CustomerEntitlement customerEntitlement;

	public CustomerEntitlement getCustomerEntitlement() {
		return customerEntitlement;
	}

	public void setCustomerEntitlement(CustomerEntitlement customerEntitlement) {
		this.customerEntitlement = customerEntitlement;
	}
	
}
