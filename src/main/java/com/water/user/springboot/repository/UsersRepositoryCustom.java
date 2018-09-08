package com.water.user.springboot.repository;

import com.water.user.springboot.document.PotentialUsers;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.document.WahterUsers;

public interface UsersRepositoryCustom {

	
	public void insertWahterUser(WahterUsers wahterUsers) throws Exception;
	
	public void insertPotentialUser(PotentialUsers potentialUsers) throws Exception;
	
	public boolean validateUserByPhoneNumber(String phoneNumber, String password) throws Exception;

	public Users getUser(String phoneNumber) throws Exception;

	public boolean updateUser(Users users) throws Exception;

	boolean validatePhoneNumber(String phoneNumber) throws Exception;
	
	
}
