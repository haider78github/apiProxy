package com.softech.ls360.web.proxy.endpoint.model.response.vu360user;

import com.softech.vu360.lms.webservice.message.lmsapi.types.transactionresult.TransactionResultType;

public class UserResponse {

	private LmsApiRegisterUser registerUser;
	private TransactionResultType transactionResultType;
	private String transactionResultMessage;
	
	public LmsApiRegisterUser getRegisterUser() {
		return registerUser;
	}
	
	public void setRegisterUser(LmsApiRegisterUser registerUser) {
		this.registerUser = registerUser;
	}

	public TransactionResultType getTransactionResultType() {
		return transactionResultType;
	}

	public void setTransactionResultType(TransactionResultType transactionResultType) {
		this.transactionResultType = transactionResultType;
	}

	public String getTransactionResultMessage() {
		return transactionResultMessage;
	}

	public void setTransactionResultMessage(String transactionResultMessage) {
		this.transactionResultMessage = transactionResultMessage;
	}
	
}
