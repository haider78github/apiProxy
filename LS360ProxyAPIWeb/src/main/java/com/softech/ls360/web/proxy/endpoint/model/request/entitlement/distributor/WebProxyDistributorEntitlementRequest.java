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
    "distributorEntitlementRequest"
})
@XmlRootElement(name="json")
public class WebProxyDistributorEntitlementRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value="DistributorEntitlementRequest")
	@XmlElement(name = "DistributorEntitlementRequest", required = true)
	private DistributorEntitlementRequest distributorEntitlementRequest;

	public DistributorEntitlementRequest getDistributorEntitlementRequest() {
		return distributorEntitlementRequest;
	}

	public void setDistributorEntitlementRequest(DistributorEntitlementRequest distributorEntitlementRequest) {
		this.distributorEntitlementRequest = distributorEntitlementRequest;
	}

	@Override
	public String toString() {
		return "WebProxyDistributorEntitlementRequest [distributorEntitlementRequest="
				+ distributorEntitlementRequest + "]";
	}
	
}
