package com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "distributor"
})
public class DistributorEntitlementRequest {

	@JsonProperty(value="Distributor")
	@XmlElement(name = "Distributor", required = true)
	private Distributor distributor;

	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	@Override
	public String toString() {
		return "DistributorEntitlementRequest [distributor=" + distributor + "]";
	}
	
}
