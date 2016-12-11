//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.25 at 07:11:10 PM PKT 
//


package com.softech.vu360.lms.webservice.message.lmsapi.types.trainingplan;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrainingPlanAssignResp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrainingPlanAssignResp"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="UserId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="TrainingPlanCourses" type="{http://trainingplan.types.lmsapi.message.webservice.lms.vu360.softech.com}TrainingPlanResponseCourses" minOccurs="0"/&gt;
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
@XmlType(name = "TrainingPlanAssignResp", propOrder = {
    "userId",
    "trainingPlanCourses"
})
public class TrainingPlanAssignResp
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "UserId", required = true)
    protected String userId;
    @XmlElement(name = "TrainingPlanCourses")
    protected TrainingPlanResponseCourses trainingPlanCourses;
    @XmlAttribute(name = "errorCode", required = true)
    protected String errorCode;
    @XmlAttribute(name = "errorMessage")
    protected String errorMessage;

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the trainingPlanCourses property.
     * 
     * @return
     *     possible object is
     *     {@link TrainingPlanResponseCourses }
     *     
     */
    public TrainingPlanResponseCourses getTrainingPlanCourses() {
        return trainingPlanCourses;
    }

    /**
     * Sets the value of the trainingPlanCourses property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrainingPlanResponseCourses }
     *     
     */
    public void setTrainingPlanCourses(TrainingPlanResponseCourses value) {
        this.trainingPlanCourses = value;
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