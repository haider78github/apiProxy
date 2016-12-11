package com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "distributorEntitlementDetailsRequest"
})
@XmlRootElement(name="json")
public class WebProxyDistributorEntitlementDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value="DistributorEntitlementDetailsRequest")
	@XmlElement(name = "DistributorEntitlementDetailsRequest", required = true)
	private DistributorEntitlementDetailsRequest distributorEntitlementDetailsRequest;

	public DistributorEntitlementDetailsRequest getDistributorEntitlementDetailsRequest() {
		return distributorEntitlementDetailsRequest;
	}

	public void setDistributorEntitlementDetailsRequest(DistributorEntitlementDetailsRequest distributorEntitlementDetailsRequest) {
		this.distributorEntitlementDetailsRequest = distributorEntitlementDetailsRequest;
	}

	@Override
	public String toString() {
		return "WebProxyDistributorEntitlementDetailsRequest [distributorEntitlementDetailsRequest="
				+ distributorEntitlementDetailsRequest + "]";
	}
	
}
