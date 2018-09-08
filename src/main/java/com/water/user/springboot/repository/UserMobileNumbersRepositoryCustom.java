package com.water.user.springboot.repository;
import com.water.user.springboot.document.UserMobileNumbers;	
	
	public interface UserMobileNumbersRepositoryCustom {

		
		public void insertUserMobileNumbers(UserMobileNumbers userMobileNumbers) throws Exception;
		
		
		/*public boolean validateUserByPhoneNumber(String phoneNumber, String password) throws Exception;

		public UserMobileNumbers getUser(String phoneNumber) throws Exception;

		public boolean updateUser(UserMobileNumbers users) throws Exception;

		boolean validatePhoneNumber(String phoneNumber) throws Exception;*/
		
		
	}

