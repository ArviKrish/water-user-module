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
import com.water.user.springboot.document.Users;
import com.water.user.springboot.exceptions.LoginException;
import com.water.user.springboot.exceptions.PhoneNumberExistsException;
import com.water.user.springboot.exceptions.ValidationException;
import com.water.user.springboot.repository.UserRepository;
import com.water.user.springboot.service.UserService;
import com.water.user.springboot.service.responsegenerator.ResponseGenerator;
import com.water.user.springboot.util.StringUtils;
import com.water.user.springboot.validator.RequestParammm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersResource<T> {

    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
    @Autowired
    private Configurations configurations;
    @Autowired
    private ResponseGenerator responseGenerator;
    
    @Autowired
    @Qualifier("myProperties")
    private Properties myProperties;

    public UsersResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<Users> getAll() {
    	
        return userRepository.findAll();
    }
    
    @GetMapping("/authenticateUser")
    @ResponseBody
    public ResponseEntity<Response> authenticateUser(@RequestParam Map<String, String> queryMap) {
    	
    	try {
    	if(!StringUtils.isValidRequest(queryMap, "phoneNumber")||!StringUtils.isValidRequest(queryMap, "password"))
    		throw new ValidationException("Phone number and Password cannot be empty");
			userService.authenticateUser(queryMap.get("phoneNumber"), queryMap.get("password"));
			return responseGenerator.createResponse(null, "Authentication Success","001",HttpStatus.OK);
		} catch (LoginException| ValidationException e) {
			return responseGenerator.createErrorResponse(e.getMessage(), "1000", HttpStatus.BAD_REQUEST, null);
		}catch (Exception e) {
			return responseGenerator.createErrorResponse(e.getMessage(), "1000", HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
    }
    
    @GetMapping("/getUser")
    @ResponseBody
    @Validated
    public ResponseEntity<Response> getUser(@RequestParam Map<String, String> queryMap) {
    	
    	try {
    		
    	if(!StringUtils.isValidRequest(queryMap, "phoneNumber"))
    		throw new ValidationException("Required parameters not provided");
			Users user = userService.getUser(queryMap.get("phoneNumber"));
			return responseGenerator.createResponse(user, null,"001",HttpStatus.OK);
		} catch (ValidationException e) {
			return responseGenerator.createErrorResponse(e.getMessage(), "1000", HttpStatus.BAD_REQUEST, null);
		}catch (Exception e) {
			return responseGenerator.createErrorResponse("Internal Server Error", "1000", HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
    }

    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public ResponseEntity<Response> createUser( @Valid  @RequestBody Users users, BindingResult bindingResult) {
    	
    	try {
    		if (bindingResult.hasErrors()) {
    		return responseGenerator.createErrorResponse("Validation Error", "1000", HttpStatus.BAD_REQUEST, bindingResult.getAllErrors());	
    		}
			userService.insertUser(users);
			return responseGenerator.createResponse(null, "Registration successful","001",HttpStatus.OK);
			
		} catch (PhoneNumberExistsException e) {
			return responseGenerator.createErrorResponse(e.getMessage(), "1000", HttpStatus.BAD_REQUEST, null);
		}			
				
    	catch (Exception e) {
    		return responseGenerator.createErrorResponse(e.getMessage(), "1000", HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
    }
    
}