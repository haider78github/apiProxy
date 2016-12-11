//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.25 at 07:11:10 PM PKT 
//


package com.softech.vu360.lms.webservice.message.lmsapi.types.trainingplan;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrainingPlanAssignResponsed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrainingPlanAssignResponsed"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TrainingPlanId" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="UserGroupId" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="TrainingPlanEnrollment" type="{http://trainingplan.types.lmsapi.message.webservice.lms.vu360.softech.com}TrainingPlanAssignResp" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="errorCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="errorMessage" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrainingPlanAssignResponsed", propOrder = {
    "trainingPlanId",
    "userGroupId",
    "trainingPlanEnrollment"
})
public class TrainingPlanAssignResponsed
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "TrainingPlanId")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger trainingPlanId;
    @XmlElement(name = "UserGroupId")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected List<BigInteger> userGroupId;
    @XmlElement(name = "TrainingPlanEnrollment")
    protected List<TrainingPlanAssignResp> trainingPlanEnrollment;
    @XmlAttribute(name = "errorCode", required = true)
    protected String errorCode;
    @XmlAttribute(name = "errorMessage")
    protected String errorMessage;

    /**
     * Gets the value of the trainingPlanId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTrainingPlanId() {
        return trainingPlanId;
    }

    /**
     * Sets the value of the trainingPlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTrainingPlanId(BigInteger value) {
        this.trainingPlanId = value;
    }

    /**
     * Gets the value of the userGroupId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userGroupId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserGroupId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getUserGroupId() {
        if (userGroupId == null) {
            userGroupId = new ArrayList<BigInteger>();
        }
        return this.userGroupId;
    }

    /**
     * Gets the value of the trainingPlanEnrollment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trainingPlanEnrollment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrainingPlanEnrollment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrainingPlanAssignResp }
     * 
     * 
     */
    public List<TrainingPlanAssignResp> getTrainingPlanEnrollment() {
        if (trainingPlanEnrollment == null) {
            trainingPlanEnrollment = new ArrayList<TrainingPlanAssignResp>();
        }
        return this.trainingPlanEnrollment;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the errorMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

}