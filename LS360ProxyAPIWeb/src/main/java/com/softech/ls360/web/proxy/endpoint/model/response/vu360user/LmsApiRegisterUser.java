package com.softech.ls360.web.proxy.endpoint.model.response.vu360user;

import com.softech.vu360.lms.webservice.message.lmsapi.types.orggroup.RegisterOrganizationalGroups;

public class LmsApiRegisterUser {

	private String firstName;
	private String lastName;
	private String emailAddress;
	private String userName;
	private RegisterOrganizationalGroups registerOrganizationalGroups;
	private String errorCode;
	private String errorMessage;
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public RegisterOrganizationalGroups getRegisterOrganizationalGroups() {
		return registerOrganizationalGroups;
	}
	
	public void setRegisterOrganizationalGroups(RegisterOrganizationalGroups registerOrganizationalGroups) {
		this.registerOrganizationalGroups = registerOrganizationalGroups;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
