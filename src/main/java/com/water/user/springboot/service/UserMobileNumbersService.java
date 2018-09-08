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
import com.water.user.springboot.document.UserMobileNumbers;
import com.water.user.springboot.document.WahterUsers;
import com.water.user.springboot.repository.UserMobileNumbersRepository;

@Service
public class UserMobileNumbersService {
	@Autowired
	private UserMobileNumbersRepository userMobileNumbersRepository;

	public void insertUserMobileNumbers(UserMobileNumbers userMobileNumbers) throws Exception {

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat(Constants.DATE_FORMAT);
		userMobileNumbers.setCreateDateTime(ft.format(dNow));
		userMobileNumbers.setLastUpdatedDateTime(ft.format(dNow));
		userMobileNumbersRepository.insertUserMobileNumbers(userMobileNumbers);
	}

}
