package com.water.user.springboot.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.water.user.springboot.document.Users;
import com.water.user.springboot.repository.UserRepositoryImpl;

@Service
public class UserService {

	
	@Autowired
	private UserRepositoryImpl userRepositoryImpl;
	
	
	public Users insertUser(Users users) throws Exception {
		
		Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

	      
		users.setCreateDateTime(ft.format(dNow));
		users.setLastUpdatedDateTime(ft.format(dNow));
		return userRepositoryImpl.insertUser(users);
	}
	
	public Users authenticateUser(String phoneNumber, String password) throws Exception {
		
		return userRepositoryImpl.validateUserByPhoneNumber(phoneNumber, password);
	}
	
}
