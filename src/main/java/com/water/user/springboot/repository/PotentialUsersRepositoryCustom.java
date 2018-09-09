package com.water.user.springboot.repository;

import com.water.user.springboot.document.PotentialUsers;
import com.water.user.springboot.document.UserMobileNumbers;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.document.WahterUsers;

public interface PotentialUsersRepositoryCustom {

	
	public boolean isPhoneNumberRegistered(String phoneNumber, String collectionName) throws Exception;
	
	public PotentialUsers getPotentialUserByPhoneNumber(String phoneNumber, String password) throws Exception;

	public UserMobileNumbers getInterestedUserByPhoneNumber(String phoneNumber) throws Exception;
	
	
}
