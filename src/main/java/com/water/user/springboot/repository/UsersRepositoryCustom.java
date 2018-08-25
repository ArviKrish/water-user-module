package com.water.user.springboot.repository;

import org.springframework.stereotype.Repository;

import com.water.user.springboot.document.Users;

public interface UsersRepositoryCustom {

	
	public void insertUser(Users users) throws Exception;
	
	public boolean validateUserByPhoneNumber(String phoneNumber, String password) throws Exception;

	public Users getUser(String phoneNumber) throws Exception;

	public boolean updateUser(Users users) throws Exception;
	
	
}
