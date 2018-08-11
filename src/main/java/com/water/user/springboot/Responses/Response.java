package com.water.user.springboot.Responses;

import java.util.List;

public class Response {
	
	private String responseCode;
	
	private String message;
	
	private String objectId;
	
	private List<ErrorMessage> errorMessages;
	
	private Object body;

	public Response() {

	}


	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public List<ErrorMessage> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<ErrorMessage> errorMessages) {
		this.errorMessages = errorMessages;
	}


	public Object getBody() {
		return body;
	}


	public void setBody(Object body) {
		this.body = body;
	}
	
	

}
