//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.25 at 07:11:10 PM PKT 
//


package com.softech.vu360.lms.webservice.message.lmsapi.types.usergroup;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdatedUserGroup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdatedUserGroup"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="OrganizationGroupId" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="NewUserGroupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdatedUserGroup", propOrder = {
    "id",
    "organizationGroupId",
    "newUserGroupName"
})
public class UpdatedUserGroup
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Id")
    protected BigInteger id;
    @XmlElement(name = "OrganizationGroupId")
    protected BigInteger organizationGroupId;
    @XmlElement(name = "NewUserGroupName")
    protected String newUserGroupName;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Gets the value of the organizationGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOrganizationGroupId() {
        return organizationGroupId;
    }

    /**
     * Sets the value of the organizationGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOrganizationGroupId(BigInteger value) {
        this.organizationGroupId = value;
    }

    /**
     * Gets the value of the newUserGroupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewUserGroupName() {
        return newUserGroupName;
    }

    /**
     * Sets the value of the newUserGroupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewUserGroupName(String value) {
        this.newUserGroupName = value;
    }

}
