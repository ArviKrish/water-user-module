package com.water.user.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.water.user.springboot.document.Users;
import com.water.user.springboot.repository.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	
	public Users inserUser(Users users) {
		
		return userDao.inserUser(users);
	}
	
}
