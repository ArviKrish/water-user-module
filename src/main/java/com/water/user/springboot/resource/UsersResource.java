package com.water.user.springboot.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.water.user.springboot.Responses.Response;
import com.water.user.springboot.config.Configurations;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.exceptions.LoginException;
import com.water.user.springboot.exceptions.PhoneNumberExistsException;
import com.water.user.springboot.repository.UserRepository;
import com.water.user.springboot.service.UserService;

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
    	
    	if(queryMap.get("phoneNumber").equalsIgnoreCase("")||queryMap.get("password").equalsIgnoreCase(""))
    		throw new LoginException("Phone number and Password cannot be empty");
        try {
			Users users = userService.authenticateUser(queryMap.get("phoneNumber"), queryMap.get("password"));
			ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(HttpStatus.ACCEPTED);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			return new ResponseEntity<Response>(new Response("001", "Authentication Success", users.getId()), responseHeaders,HttpStatus.OK);
		} catch (LoginException e) {
			throw new LoginException(e.getMessage());
		}catch (Exception e) {
			throw new InternalError(e.getMessage());
		}
    }
    
    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public ResponseEntity<Response> createUser( @Valid  @RequestBody Users users) {
    	
    	try {
			userService.insertUser(users);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			return new ResponseEntity<Response>(new Response("001", "Registration successful", users.getId()), responseHeaders,HttpStatus.OK);
			
		} catch (PhoneNumberExistsException e) {
			throw new PhoneNumberExistsException(e.getMessage());
		}
    	catch (Exception e) {
    		throw new InternalError(e.getMessage());
		}
    }
    
}