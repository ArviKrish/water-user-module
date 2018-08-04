package com.water.user.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.water.user.springboot.document.Users;
import com.water.user.springboot.repository.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	
	public Users insertUser(Users users) {
		
		return userDao.inserUser(users);
	}
	
	public Users authenticateUser(String phoneNumber, String password) {
		
		return userDao.validateUserByPhoneNumber(phoneNumber, password);
	}
	
}
