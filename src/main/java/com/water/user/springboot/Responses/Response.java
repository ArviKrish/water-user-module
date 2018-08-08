package com.water.user.springboot.Responses;

public class Response {
	
	private String responseCode;
	
	private String message;
	
	private String objectId;
	
	

	public Response() {

	}

	public Response(String responseCode, String message, String objectId) {
		super();
		this.responseCode = responseCode;
		this.message = message;
		this.objectId = objectId;
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
	
	

}
