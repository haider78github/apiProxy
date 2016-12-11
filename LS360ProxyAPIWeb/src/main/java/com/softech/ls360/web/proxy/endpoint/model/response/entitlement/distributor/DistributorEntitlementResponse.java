package com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DistributorEntitlementResponse", propOrder = {
    "distributorEntitlements"
})
@JsonInclude(value=Include.NON_NULL)
public class DistributorEntitlementResponse {

	@JsonProperty(value="ErrorCode")
	@XmlAttribute(name = "ErrorCode")
	private String errorCode;
	
	@JsonProperty(value="ErrorMessage")
	@XmlAttribute(name = "ErrorMessage")
	private String errorMessage;
	
	@JsonProperty(value="DistributorEntitlements")
	@XmlElement(name = "DistributorEntitlements")
    private DistributorEntitlements distributorEntitlements;
    
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public DistributorEntitlements getDistributorEntitlements() {
		return distributorEntitlements;
	}
	
	public void setDistributorEntitlements(DistributorEntitlements distributorEntitlements) {
		this.distributorEntitlements = distributorEntitlements;
	}
	
	@Override
	public String toString() {
		return "DistributorEntitlementResponse [errorCode=" + errorCode
				+ ", errorMessage=" + errorMessage
				+ ", DistributorEntitlements=" + distributorEntitlements + "]";
	}
	
}
