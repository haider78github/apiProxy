//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.25 at 07:11:10 PM PKT 
//


package com.softech.vu360.lms.webservice.message.lmsapi.types.usergroup;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetUserGroupIdByName complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetUserGroupIdByName"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ParentOrgGroupHierarchy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetUserGroupIdByName", propOrder = {
    "parentOrgGroupHierarchy",
    "name"
})
public class GetUserGroupIdByName
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "ParentOrgGroupHierarchy", required = true)
    protected String parentOrgGroupHierarchy;
    @XmlElement(name = "Name", required = true)
    protected String name;

    /**
     * Gets the value of the parentOrgGroupHierarchy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentOrgGroupHierarchy() {
        return parentOrgGroupHierarchy;
    }

    /**
     * Sets the value of the parentOrgGroupHierarchy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentOrgGroupHierarchy(String value) {
        this.parentOrgGroupHierarchy = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
