package com.softech.ls360.web.proxy.endpoint.model.request.entitlement.customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(Include.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", propOrder = {
    "customerGuid",
    "customerCode"
})
public class Customer  {

	@JsonProperty(value="CustomerGuid")
	@XmlElement(name = "CustomerGuid")
	private String customerGuid;
	
	@JsonProperty(value="CustomerCode")
	@XmlElement(name = "CustomerCode")
	private String customerCode;

	public String getCustomerGuid() {
		return customerGuid;
	}

	public void setCustomerGuid(String customerGuid) {
		this.customerGuid = customerGuid;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
}
