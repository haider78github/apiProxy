//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.25 at 07:11:10 PM PKT 
//

package com.softech.vu360.lms.webservice.message.lmsapi.types.enrollment;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2001.xmlschema.Adapter2;

/**
 * <p>
 * Java class for LearnerEnrollCourses complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="LearnerEnrollCourses"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CourseId" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="enrollmentStartDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *       &lt;attribute name="enrollmentEndDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LearnerEnrollCourses", propOrder = { "courseId" })
public class LearnerEnrollCourses implements Serializable {

	private final static long serialVersionUID = 1L;
	@XmlElement(name = "CourseId", required = true)
	protected List<String> courseId;
	@XmlAttribute(name = "enrollmentStartDate", required = true)
	@XmlJavaTypeAdapter(Adapter2.class)
	@XmlSchemaType(name = "date")
	protected LocalDate enrollmentStartDate;
	@XmlAttribute(name = "enrollmentEndDate", required = true)
	@XmlJavaTypeAdapter(Adapter2.class)
	@XmlSchemaType(name = "date")
	protected LocalDate enrollmentEndDate;

	/**
	 * Gets the value of the courseId property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the courseId property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getCourseId().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	public List<String> getCourseId() {
		if (courseId == null) {
			courseId = new ArrayList<String>();
		}
		return this.courseId;
	}

	public void setCourseId(List<String> courseId) {
		this.courseId = courseId;
	}

	/**
	 * Gets the value of the enrollmentStartDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public LocalDate getEnrollmentStartDate() {
		return enrollmentStartDate;
	}

	/**
	 * Sets the value of the enrollmentStartDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEnrollmentStartDate(LocalDate value) {
		this.enrollmentStartDate = value;
	}

	/**
	 * Gets the value of the enrollmentEndDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public LocalDate getEnrollmentEndDate() {
		return enrollmentEndDate;
	}

	/**
	 * Sets the value of the enrollmentEndDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEnrollmentEndDate(LocalDate value) {
		this.enrollmentEndDate = value;
	}

}
