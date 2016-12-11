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
    "customer"
})
@XmlRootElement(name = "CustomerEntitlementRequest")
public class CustomerEntitlementRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value="Customer")
	@XmlElement(name = "Customer", required = true)
	protected Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
