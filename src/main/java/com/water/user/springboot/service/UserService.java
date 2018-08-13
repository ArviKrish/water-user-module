package com.water.user.springboot.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.water.user.springboot.constants.Constants;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.repository.UsersRepository;

@Service
public class UserService {

	@Autowired
	private UsersRepository repository;
	
	public Users insertUser(Users users) throws Exception {
		
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat (Constants.DATE_FORMAT);
		users.setCreateDateTime(ft.format(dNow));
		users.setLastUpdatedDateTime(ft.format(dNow));
		return repository.insertUser(users);
	}
	
	public boolean authenticateUser(String phoneNumber, String password) throws Exception {
		
		return repository.validateUserByPhoneNumber(phoneNumber, password);
	}

	public Users getUser(String phoneNumber) throws Exception {
		
		return repository.findByPhoneNumber(phoneNumber);
	}
	
	
	
}
