package com.water.user.springboot.Responses;

public class ErrorMessage {
	
	private String errorCode;	
	
	private String errorMessage;
	
	private String field;
	
	private String objectId;

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

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	

	
}
