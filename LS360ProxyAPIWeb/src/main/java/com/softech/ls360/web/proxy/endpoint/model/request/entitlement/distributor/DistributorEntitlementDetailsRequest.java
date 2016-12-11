package com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "distributorEntitlement"
})
public class DistributorEntitlementDetailsRequest {

	@JsonProperty(value="DistributorEntitlement")
	@XmlElement(name = "DistributorEntitlement", required = true)
	private DistributorEntitlement distributorEntitlement;

	public DistributorEntitlement getDistributorEntitlement() {
		return distributorEntitlement;
	}

	public void setDistributorEntitlement(DistributorEntitlement distributorEntitlement) {
		this.distributorEntitlement = distributorEntitlement;
	}

	@Override
	public String toString() {
		return "DistributorEntitlementDetailsRequest [distributorEntitlement="
				+ distributorEntitlement + "]";
	}
	
}
