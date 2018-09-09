package com.water.user.springboot.repository;

import com.water.user.springboot.document.PotentialUsers;
import com.water.user.springboot.document.UserMobileNumbers;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.document.WahterUsers;

public interface WahterUsersRepositoryCustom {

	
	public boolean isPhoneNumberRegistered(String phoneNumber, String collectionName) throws Exception;

	public boolean updateWahterUser(WahterUsers wahterUsers ) throws Exception;

	public WahterUsers getWahterUserByPhoneNumber(String phoneNumber, String password) throws Exception;
	
}
