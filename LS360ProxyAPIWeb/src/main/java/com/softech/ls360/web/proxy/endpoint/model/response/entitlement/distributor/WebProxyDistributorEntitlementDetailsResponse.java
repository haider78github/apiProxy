package com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "distributorEntitlementDetailsResponse"
})
@XmlRootElement(name = "json")
public class WebProxyDistributorEntitlementDetailsResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value="DistributorEntitlementDetailsResponse")
	@XmlElement(name = "DistributorEntitlementDetailsResponse")
	private DistributorEntitlementDetailsResponse distributorEntitlementDetailsResponse;

	public DistributorEntitlementDetailsResponse getDistributorEntitlementDetailsResponse() {
		return distributorEntitlementDetailsResponse;
	}

	public void setDistributorEntitlementDetailsResponse(DistributorEntitlementDetailsResponse distributorEntitlementDetailsResponse) {
		this.distributorEntitlementDetailsResponse = distributorEntitlementDetailsResponse;
	}

	@Override
	public String toString() {
		return "WebProxyDistributorEntitlementDetailsResponse [distributorEntitlementDetailsResponse="
				+ distributorEntitlementDetailsResponse + "]";
	}
	
}
