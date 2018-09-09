package com.water.user.springboot.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.water.user.springboot.validator.Phone;

@Document(collection = "User_mobile_numbers")
public class UserMobileNumbers {

	@Id
	private String id;

	@Phone()
	private String phoneNumber;

	private String createDateTime;

	private String lastUpdatedDateTime;
	
	private Boolean numberVerified;

	public Boolean getNumberVerified() {
		return numberVerified;
	}

	public void setNumberVerified(Boolean numberVerified) {
		this.numberVerified = numberVerified;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
	}

	public void setLastUpdatedDateTime(String lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}