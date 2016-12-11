package com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "distributorEntitlementResponse"
})
@XmlRootElement(name = "json")
public class WebProxyDistributorEntitlementResponse {

	@JsonProperty(value="DistributorEntitlementResponse")
	@XmlElement(name = "DistributorEntitlementResponse")
	private DistributorEntitlementResponse distributorEntitlementResponse;

	public DistributorEntitlementResponse getDistributorEntitlementResponse() {
		return distributorEntitlementResponse;
	}

	public void setDistributorEntitlementResponse(DistributorEntitlementResponse distributorEntitlementResponse) {
		this.distributorEntitlementResponse = distributorEntitlementResponse;
	}

	@Override
	public String toString() {
		return "WebProxyDistributorEntitlementResponse [distributorEntitlementResponse="
				+ distributorEntitlementResponse + "]";
	}
	
}
