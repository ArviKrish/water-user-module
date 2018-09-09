package com.water.user.springboot.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.water.user.springboot.Responses.Response;
import com.water.user.springboot.config.Messages;
import com.water.user.springboot.constants.Constants;
import com.water.user.springboot.document.PotentialUsers;
import com.water.user.springboot.document.UserMobileNumbers;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.document.WahterUsers;
import com.water.user.springboot.exceptions.LoginException;
import com.water.user.springboot.exceptions.PhoneNumberExistsException;
import com.water.user.springboot.exceptions.ValidationException;
import com.water.user.springboot.repository.PotentialUsersRepository;
import com.water.user.springboot.repository.UserMobileNumbersRepository;
import com.water.user.springboot.repository.WahterUsersRepository;
import com.water.user.springboot.service.responsegenerator.ResponseGenerator;


/**
* The UserService class provides the service methods for different 
* user level operations. The Users repositories and Response Generators
* are injected by autowired IOC
*
* @author  Senthilmurugan Paranjothi
* @version 1.0
* @since   2018-09-09 
*/


@Service
public class UserService {

	@Autowired
	private WahterUsersRepository wahterUsersRepository;
	
	@Autowired
	private PotentialUsersRepository potentialUsersRepository;
	
	@Autowired
	private UserMobileNumbersRepository userMobileNumbersRepository;
	
	@Autowired
    private ResponseGenerator responseGenerator;
	
	@Autowired
	Messages messages;
	
	public void insertPotentialUser(PotentialUsers potentialUsers) throws Exception {
		
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat (Constants.DATE_FORMAT);
	    potentialUsers.setCreateDateTime(ft.format(dNow));
	    potentialUsers.setLastUpdatedDateTime(ft.format(dNow));
	    if (potentialUsersRepository.isPhoneNumberRegistered(potentialUsers.getPhoneNumber(), Constants.COLLECTION_POTENTIAL_USERS)) {
			throw new PhoneNumberExistsException(messages.get("phonenumer.already.registered"));
		}
	    potentialUsersRepository.save(potentialUsers);
	}
	
	public void insertWahterUser(WahterUsers wahterUsers) throws Exception {	
		
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat (Constants.DATE_FORMAT);
	    wahterUsers.setCreateDateTime(ft.format(dNow));
	    wahterUsers.setLastUpdatedDateTime(ft.format(dNow));
	    if (wahterUsersRepository.isPhoneNumberRegistered(wahterUsers.getPhoneNumber(), Constants.COLLECTION_WAHTER_USERS)) {
			throw new PhoneNumberExistsException(messages.get("phonenumer.already.registered"));
		}
		wahterUsersRepository.save(wahterUsers);
	}
	
	public boolean authenticateUser(String phoneNumber, String password) throws Exception {
		
		WahterUsers waterUser = wahterUsersRepository.getWahterUserByPhoneNumber(phoneNumber, password);
		if (waterUser != null) {
			if (waterUser.getActive()) {
				return true;	
			} else {
				throw new LoginException("Your registraion is under review. Our team will notity you shortly", Constants.ERROR_CODE_1102);
			}
		}
		PotentialUsers potentialUser = potentialUsersRepository.getPotentialUserByPhoneNumber(phoneNumber, password);
		if (potentialUser != null) {
			if (potentialUser.getIsRekkiCleared()) {
				throw new LoginException("Please Sign up before loging in", Constants.ERROR_CODE_1105);
			} else {
				throw new LoginException("Your registraion is being processed. Our team will notity you shortly", Constants.ERROR_CODE_1103);
			}
		}
		
		throw new LoginException(messages.get("login.fail.incorrect.credentials"), Constants.ERROR_CODE_1106);
	}

	public Users getWahterUser(String phoneNumber) throws Exception {
		
		return wahterUsersRepository.findByPhoneNumber(phoneNumber);
	}

	public boolean updateWahterUser(WahterUsers wahterUser) throws Exception {

		if(!wahterUsersRepository.updateWahterUser(wahterUser))
				throw new ValidationException(messages.get("user.not.found"));
		return true;
	}

	/**
	   * This methods validates the phone number to check if the number
	   * is registered in the WAHTER system at different level
	   * @param phoneNumber String.
	   * @return {@link ResponseEntity}
	   * <pre>
	   * responseCode : 001 - Rekki has been completed. User is ready for Sign up.
	   * responseCode : 002 - Phone number is not available in the system.
	   * </pre>
	   * @exception LoginException On validation error.
	   * <pre>
	   * errorCode : 1101 - Phone number is registered as Wahter User and active.
	   * errorCode : 1102 - Phone number is registered as Wahter User and inactive.
	   * errorCode : 1103 - Phone number is registered as Potential User and Rekki not cleared.
	   * errorCode : 1104 - Phone number is registered as Interested User.
	   * </pre>
	   */
	public ResponseEntity<Response> validatePhoneNumber(String phoneNumber) throws Exception {

		//WahterUsers waterUser = repository.getWahterUserByPhoneNumber(phoneNumber, null);
		WahterUsers waterUser = wahterUsersRepository.findByPhoneNumber(phoneNumber);
		if (waterUser != null) {
			if (waterUser.getActive()) {
				throw new LoginException("Phone number is already registered... Please login", Constants.ERROR_CODE_1101);
			} else {
				throw new LoginException("Your registraion is under review. Our team will notity you shortly", Constants.ERROR_CODE_1102);
			}
		}
		PotentialUsers potentialUser = potentialUsersRepository.findByPhoneNumber(phoneNumber);
		if (potentialUser != null) {
			if (potentialUser.getIsRekkiCleared()) {
				return responseGenerator.createResponse(null, "Registration can be taken.", Constants.RESPONSE_CODE_001, HttpStatus.OK);
			} else {
				throw new LoginException("Your registraion is being processed. Our team will notity you shortly", Constants.ERROR_CODE_1103);
			}
		}
		UserMobileNumbers userMobileNumbers = userMobileNumbersRepository.findByPhoneNumber(phoneNumber);
		if (userMobileNumbers != null) {
				throw new LoginException("Your contact number is recorded. Our team will reach you shortly", Constants.ERROR_CODE_1104);
			} 
		
		return responseGenerator.createResponse(null, "Phone number can be taken", Constants.RESPONSE_CODE_002, HttpStatus.OK);
		
	}
	
	
	
}
