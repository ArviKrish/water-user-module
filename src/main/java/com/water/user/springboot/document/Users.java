package com.water.user.springboot.document;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.water.user.springboot.validator.Phone;

@Document
public class Users {

	@Id
    private String id;
    
    @NotEmpty
    private String emailId;
    
    @NotEmpty
    private String organizationName;
    
    @NotEmpty
    private String organizationAddress;
    
    @NotEmpty
    private String password;    
    
    @Phone
    private String phoneNumber;
    
    public Users() {
	}

	public Users(String id, String emailId, String organizationName, String organizationAddress,
			String password, String phoneNumber) {
		super();
		this.id = id;
		this.emailId = emailId;
		this.organizationName = organizationName;
		this.organizationAddress = organizationAddress;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationAddress() {
		return organizationAddress;
	}

	public void setOrganizationAddress(String organizationAddress) {
		this.organizationAddress = organizationAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
}
