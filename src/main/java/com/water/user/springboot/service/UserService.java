package com.water.user.springboot.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.water.user.springboot.constants.Constants;
import com.water.user.springboot.document.PotentialUsers;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.document.WahterUsers;
import com.water.user.springboot.repository.UsersRepository;

@Service
public class UserService {

	@Autowired
	private UsersRepository repository;
	
	public void insertPotentialUser(PotentialUsers potential_users) throws Exception {
		
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat (Constants.DATE_FORMAT);
	    potential_users.setCreateDateTime(ft.format(dNow));
	    potential_users.setLastUpdatedDateTime(ft.format(dNow));
	    potential_users.setIsRekkiCleared(false);
		repository.insertPotentialUser(potential_users);
	}
	
	public void insertWahterUser(WahterUsers wahterUsers) throws Exception {	
		
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat (Constants.DATE_FORMAT);
	    wahterUsers.setCreateDateTime(ft.format(dNow));
	    wahterUsers.setLastUpdatedDateTime(ft.format(dNow));
		repository.insertWahterUser(wahterUsers);
	}
	
	public boolean authenticateUser(String phoneNumber, String password) throws Exception {
		
		return repository.validateUserByPhoneNumber(phoneNumber, password);
	}

	public Users getUser(String phoneNumber) throws Exception {
		
		return repository.findByPhoneNumber(phoneNumber);
	}

	public boolean updateUser(Users users) throws Exception {

		return repository.updateUser(users);
	}

	public boolean validatePhoneNumber(String phoneNumber) throws Exception {
		return repository.validatePhoneNumber(phoneNumber);
		}
	
	
	
}
