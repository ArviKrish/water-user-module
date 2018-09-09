package com.water.user.springboot.repository;
import com.water.user.springboot.document.UserMobileNumbers;	
	
	public interface UserMobileNumbersRepositoryCustom {

		public boolean isPhoneNumberRegistered(String phoneNumber, String collectionName) throws Exception;
		
	}

