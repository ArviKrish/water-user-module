package com.water.user.springboot.document;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.water.user.springboot.validator.Phone;

@Document
public class Users extends DTO {

	@Id
    private String id;
    
	@Phone()
	private String phoneNumber;
	
	private String alternatePhoneNumber;
	
	private String contactPersonPrimary;
	
	private String contactPersonSecondary;
	
	private String password;  
	
	@NotEmpty(message="Name cannot be empty")
	@NotNull(message="Name cannot be null")
	@Size(min=10, message="Name should have atleast 10 characters")
	private String organizationName;
	
    private String emailId;
    
    private Address address;
    
    private List<Address> deliveryAddresses;
    
    private String GSTNumber;
    
    @Value("${prefered.payment.type:CODY}")
    private String preferedPaymentType;
    
    private String createDateTime;
    
    private String lastUpdatedDateTime;
    
    private Boolean active;
    
    

	public Users() {
	}

	public Users(String id, String phoneNumber, String alternatePhoneNumber, String contactPersonPrimary,
			String contactPersonSecondary, String password, String organizationName, String emailId,
			Address address, List<Address> deliveryAddresses, String gSTNumber, String preferedPaymentType,
			String createDateTime, String lastUpdatedDateTime, Boolean active) {
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
		this.createDateTime = createDateTime;
		this.lastUpdatedDateTime = lastUpdatedDateTime;
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
