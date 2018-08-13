package com.water.user.springboot.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.water.user.springboot.Responses.ErrorMessage;
import com.water.user.springboot.Responses.Response;
import com.water.user.springboot.config.Configurations;
import com.water.user.springboot.config.Messages;
import com.water.user.springboot.constants.Constants;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.exceptions.LoginException;
import com.water.user.springboot.exceptions.PhoneNumberExistsException;
import com.water.user.springboot.exceptions.ValidationException;
import com.water.user.springboot.service.UserService;
import com.water.user.springboot.service.responsegenerator.ResponseGenerator;
import com.water.user.springboot.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersResource<T> {

    @Autowired
    private UserService userService;
    @Autowired
    private Configurations configurations;
    @Autowired
    private ResponseGenerator responseGenerator;
    @Autowired
    Messages messages;
    
    @Autowired
    @Qualifier("myProperties")
    private Properties myProperties;

    @GetMapping("/authenticateUser")
    @ResponseBody
    public ResponseEntity<Response> authenticateUser(@RequestParam Map<String, String> queryMap) {
    	
    	try {
    	if(!StringUtils.isValidRequest(queryMap, Constants.PHONE_NUMBER)||!StringUtils.isValidRequest(queryMap, Constants.PASSWORD))
    		throw new ValidationException(messages.get("phonenumber.password.missing"));
			userService.authenticateUser(queryMap.get(Constants.PHONE_NUMBER), queryMap.get(Constants.PASSWORD));
			return responseGenerator.createResponse(null, messages.get("authentication.success"),Constants.ERROR_CODE_001,HttpStatus.OK);
		} catch (LoginException| ValidationException e) {
			return responseGenerator.createErrorResponse(e.getMessage(), Constants.ERROR_CODE_1000, HttpStatus.BAD_REQUEST, null);
		}catch (Exception e) {
			return responseGenerator.createErrorResponse(e.getMessage(), Constants.ERROR_CODE_1000, HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
    }
    
    @GetMapping("/getUser")
    @ResponseBody
    @Validated
    public ResponseEntity<Response> getUser(@RequestParam Map<String, String> queryMap) {
    	
    	try {
    		
    	if(!StringUtils.isValidRequest(queryMap, Constants.PHONE_NUMBER))
    		throw new ValidationException(messages.get("paramertes.not.provided"));
			Users user = userService.getUser(queryMap.get(Constants.PHONE_NUMBER));
			if(user == null)
			throw new ValidationException(messages.get("user.not.found"));
			return responseGenerator.createResponse(user, null,Constants.ERROR_CODE_001,HttpStatus.OK);
		} catch (ValidationException e) {
			return responseGenerator.createErrorResponse(e.getMessage(), Constants.ERROR_CODE_1000, HttpStatus.BAD_REQUEST, null);
		}catch (Exception e) {
			return responseGenerator.createErrorResponse(messages.get("internal.server.error"), Constants.ERROR_CODE_1000, HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
    }

    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public ResponseEntity<Response> createUser( @Valid  @RequestBody Users users, BindingResult bindingResult) {
    	
    	try {
    		if (bindingResult.hasErrors()) {
    		return responseGenerator.createErrorResponse(messages.get("validation.error"), Constants.ERROR_CODE_1000, HttpStatus.BAD_REQUEST, bindingResult.getAllErrors());	
    		}
			userService.insertUser(users);
			return responseGenerator.createResponse(null, messages.get("registration.succesful"), Constants.ERROR_CODE_001, HttpStatus.OK);
			
		} catch (PhoneNumberExistsException e) {
			return responseGenerator.createErrorResponse(e.getMessage(), Constants.ERROR_CODE_1000, HttpStatus.BAD_REQUEST, null);
		}			
				
    	catch (Exception e) {
    		return responseGenerator.createErrorResponse(e.getMessage(), Constants.ERROR_CODE_1000, HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
    }
    
}