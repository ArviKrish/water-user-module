package com.water.user.springboot.repository;

import org.springframework.stereotype.Repository;

import com.water.user.springboot.document.Users;

public interface UsersRepositoryCustom {

	
	public Users insertUser(Users users) throws Exception;
	
	public boolean validateUserByPhoneNumber(String phoneNumber, String password) throws Exception;

	public Users getUser(String phoneNumber) throws Exception;
	
	
}
