package com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DistributorEntitlementDetailsResponse", propOrder = {
    "distributorEntitlements"
})
@JsonInclude(Include.NON_NULL)
public class DistributorEntitlementDetailsResponse {

	@JsonProperty(value="DistributorEntitlement")
	private DistributorEntitlement respDistributorEntitlement;
	
	@JsonProperty(value="ErrorCode")
    @XmlAttribute(name = "ErrorCode")
    private String errorCode;
	
	@JsonProperty(value="ErrorMessage")
	@XmlAttribute(name = "ErrorMessage")
	private String errorMessage;

	public DistributorEntitlement getRespDistributorEntitlement() {
		return respDistributorEntitlement;
	}

	public void setRespDistributorEntitlement(DistributorEntitlement respDistributorEntitlement) {
		this.respDistributorEntitlement = respDistributorEntitlement;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
    
}
