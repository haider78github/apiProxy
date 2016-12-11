package com.softech.ls360.web.proxy.endpoint.model.response.entitlement.distributor;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.softech.ls360.lms.proxy.entities.DistributorEntitlement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DistributorEntitlements", propOrder = {
    "distributorEntitlementList"
})
//@JsonInclude(value=Include.NON_NULL)
public class DistributorEntitlements {

	@JsonProperty(value="DistributorEntitlement")
	@XmlElement(name = "DistributorEntitlement", required = true, nillable = true)
	private List<DistributorEntitlement> distributorEntitlementList;

	public List<DistributorEntitlement> getDistributorEntitlementList() {
		return distributorEntitlementList;
	}

	public void setDistributorEntitlementList(List<DistributorEntitlement> distributorEntitlementList) {
		this.distributorEntitlementList = distributorEntitlementList;
	}

	@Override
	public String toString() {
		return "DistributorEntitlements [distributorEntitlementList=" + distributorEntitlementList + "]";
	}
	
}
