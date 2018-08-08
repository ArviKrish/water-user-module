package com.water.user.springboot.document;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Convert;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.water.user.springboot.config.LocalDateTimeConverter;
import com.water.user.springboot.validator.Phone;

@Document
public class Users {

	@Id
    private String id;
    
	@Phone
	private String phoneNumber;
	
	private String alternatePhoneNumber;
	
	private String contactPersonPrimary;
	
	private String contactPersonSecondary;
	
	@NotEmpty
	private String password;  
	
	@NotEmpty
	private String organizationName;
	
    @NotEmpty
    private String emailId;
    
    private Address address;
    
    private List<Address> deliveryAddresses;
    
    private String GSTNumber;
    
    private String preferedPaymentType;
    
    /*@DateTimeFormat(iso=ISO.DATE_TIME)
    private LocalDateTime createDateTime;
    
    @DateTimeFormat(iso=ISO.DATE_TIME)
    private LocalDateTime lastUpdatedDateTime;*/
    
    private Boolean active;
    
    

	public Users() {
	}

	public Users(String id, String phoneNumber, String alternatePhoneNumber, String contactPersonPrimary,
			String contactPersonSecondary, String password, String organizationName, String emailId,
			Address address, List<Address> deliveryAddresses, String gSTNumber, String preferedPaymentType,
			/*LocalDateTime createDateTime, LocalDateTime lastUpdatedDateTime,*/ Boolean active) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.alternatePhoneNumber = alternatePhoneNumber;
		this.contactPersonPrimary = contactPersonPrimary;
		this.contactPersonSecondary = contactPersonSecondary;
		this.password = password;
		this.organizationName = organizationName;
		this.emailId = emailId;
		this.address = address;
		this.setDeliveryAddresses(deliveryAddresses);
		this.GSTNumber = gSTNumber;
		this.preferedPaymentType = preferedPaymentType;
	/*	this.createDateTime = createDateTime;
		this.lastUpdatedDateTime = lastUpdatedDateTime;*/
		this.active = active;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAlternatePhoneNumber() {
		return alternatePhoneNumber;
	}

	public void setAlternatePhoneNumber(String alternatePhoneNumber) {
		this.alternatePhoneNumber = alternatePhoneNumber;
	}

	public String getContactPersonPrimary() {
		return contactPersonPrimary;
	}

	public void setContactPersonPrimary(String contactPersonPrimary) {
		this.contactPersonPrimary = contactPersonPrimary;
	}

	public String getContactPersonSecondary() {
		return contactPersonSecondary;
	}

	public void setContactPersonSecondary(String contactPersonSecondary) {
		this.contactPersonSecondary = contactPersonSecondary;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getGSTNumber() {
		return GSTNumber;
	}

	public void setGSTNumber(String gSTNumber) {
		GSTNumber = gSTNumber;
	}

	public String getPreferedPaymentType() {
		return preferedPaymentType;
	}

	public void setPreferedPaymentType(String preferedPaymentType) {
		this.preferedPaymentType = preferedPaymentType;
	}

	/*public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
	}

	public void setLastUpdatedDateTime(LocalDateTime lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}*/

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Address> getDeliveryAddresses() {
		return deliveryAddresses;
	}

	public void setDeliveryAddresses(List<Address> deliveryAddresses) {
		this.deliveryAddresses = deliveryAddresses;
	}
    
    
    
   

    
}
