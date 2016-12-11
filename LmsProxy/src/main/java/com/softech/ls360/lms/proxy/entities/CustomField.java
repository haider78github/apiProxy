package com.softech.ls360.lms.proxy.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CustomField extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String HORIZONTAL = "horizonatl";
	public static final String VERTICAL = "vertical";

	private boolean fieldEncryptedTf;
	private String fieldLabel;
	private boolean fieldRequiredTf;
	private String fieldType;
	private Long customerFieldContextId;
	private String description;
	private String textCustomerFieldType;
	private String alignment = HORIZONTAL;
	private boolean checkBoxTf;
	private boolean globalTf = true;
	private boolean active;

	public boolean isFieldEncryptedTf() {
		return fieldEncryptedTf;
	}

	public void setFieldEncryptedTf(boolean fieldEncryptedTf) {
		this.fieldEncryptedTf = fieldEncryptedTf;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	public boolean isFieldRequiredTf() {
		return fieldRequiredTf;
	}

	public void setFieldRequiredTf(boolean fieldRequiredTf) {
		this.fieldRequiredTf = fieldRequiredTf;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	@Column(name="CUSTOMFIELDCONTXT_ID")
	public Long getCustomerFieldContextId() {
		return customerFieldContextId;
	}

	public void setCustomerFieldContextId(Long customerFieldContextId) {
		this.customerFieldContextId = customerFieldContextId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTextCustomerFieldType() {
		return textCustomerFieldType;
	}

	public void setTextCustomerFieldType(String textCustomerFieldType) {
		this.textCustomerFieldType = textCustomerFieldType;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public boolean isCheckBoxTf() {
		return checkBoxTf;
	}

	public void setCheckBoxTf(boolean checkBoxTf) {
		this.checkBoxTf = checkBoxTf;
	}

	@Column(name="ISGLOBALTF")
	public boolean isGlobalTf() {
		return globalTf;
	}

	public void setGlobalTf(boolean globalTf) {
		this.globalTf = globalTf;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}