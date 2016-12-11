package com.softech.ls360.web.proxy.endpoint.model.request.entitlement.distributor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Distributor", propOrder = {
    "id"
})
public class Distributor {

	@JsonProperty(value="Id")
	@XmlElement(name = "Id", required = true)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DistributorEntitlement [id=" + id + "]";
	}
	
}
