package com.water.user.springboot.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.water.user.springboot.Responses.Response;
import com.water.user.springboot.config.Configurations;
import com.water.user.springboot.config.Messages;
import com.water.user.springboot.constants.Constants;
import com.water.user.springboot.document.PotentialUsers;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.document.WahterUsers;
import com.water.user.springboot.exceptions.UnknownException;
import com.water.user.springboot.exceptions.ValidationException;
import com.water.user.springboot.service.UserService;
import com.water.user.springboot.service.responsegenerator.ResponseGenerator;
import com.water.user.springboot.util.NotificationUtil;
import com.water.user.springboot.util.StringUtils;

import java.util.Map;
import java.util.Properties;

import javax.management.Notification;
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

    @RequestMapping(value = "/authenticateUser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Response> authenticateUser(@RequestParam Map<String, String> queryMap) throws Exception {
    	
    	if(!StringUtils.isValidRequest(queryMap, Constants.PHONE_NUMBER)||!StringUtils.isValidRequest(queryMap, Constants.PASSWORD))
    		throw new ValidationException(messages.get("phonenumber.password.missing"));
			userService.authenticateUser(queryMap.get(Constants.PHONE_NUMBER), queryMap.get(Constants.PASSWORD));
			return responseGenerator.createResponse(null, messages.get("authentication.success"),Constants.ERROR_CODE_001,HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/sendNotification", method = RequestMethod.GET)
    public String sendNotification(@RequestParam Map<String, String> queryMap) throws Exception {
    	
    	NotificationUtil.sendNotification();
    		
    	return "Completed";
    }
    
    
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    @Validated
    public ResponseEntity<Response> getUser(@RequestParam Map<String, String> queryMap) throws Exception {
    	
    		if(!StringUtils.isValidRequest(queryMap, Constants.PHONE_NUMBER))
    		throw new ValidationException(messages.get("paramertes.not.provided"));
			Users user = userService.getUser(queryMap.get(Constants.PHONE_NUMBER));
			if(user == null)
			throw new ValidationException(messages.get("user.not.found"));
			return responseGenerator.createResponse(user, null,Constants.ERROR_CODE_001,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/validatePhoneNumber", method = RequestMethod.GET)
    @ResponseBody
    @Validated
    public ResponseEntity<Response> validatePhoneNumber(@RequestParam Map<String, String> queryMap) throws Exception {
    	
    		if(!StringUtils.isValidRequest(queryMap, Constants.PHONE_NUMBER))
    		throw new ValidationException(messages.get("paramertes.not.provided"));
    		userService.validatePhoneNumber(queryMap.get(Constants.PHONE_NUMBER));
			return responseGenerator.createResponse(null, "Phone nmber is not registered",Constants.ERROR_CODE_001,HttpStatus.OK);
     }
    
    

    @RequestMapping(value = "/createwahteruser", method = RequestMethod.POST)
    public ResponseEntity<Response> createWahteruser( @Valid  @RequestBody WahterUsers wahterUsers, BindingResult bindingResult) throws Exception {
    	
    		if (bindingResult.hasErrors()) {
    		return responseGenerator.createErrorResponse(messages.get("validation.error"), Constants.ERROR_CODE_1000, HttpStatus.BAD_REQUEST, bindingResult.getAllErrors());	
    		}
			userService.insertWahterUser(wahterUsers);
			return responseGenerator.createResponse(null, messages.get("registration.succesful"), Constants.ERROR_CODE_001, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/createpotentialuser", method = RequestMethod.POST)
    public ResponseEntity<Response> createPotentialsUser( @Valid  @RequestBody PotentialUsers potentialUsers, BindingResult bindingResult) throws Exception {
    	
    		if (bindingResult.hasErrors()) {
    		return responseGenerator.createErrorResponse(messages.get("validation.error"), Constants.ERROR_CODE_1000, HttpStatus.BAD_REQUEST, bindingResult.getAllErrors());	
    		}
			userService.insertPotentialUser(potentialUsers);
			return responseGenerator.createResponse(null, messages.get("registration.succesful"), Constants.ERROR_CODE_001, HttpStatus.OK);
    }
    
    
    
    @RequestMapping(value = "/updateuser", method = RequestMethod.PATCH)
    public ResponseEntity<Response> updateUser(@RequestBody Users users) throws Exception {
    	
    		if(userService.updateUser(users))
    		return responseGenerator.createResponse(null, messages.get("update.succesful"), Constants.ERROR_CODE_001, HttpStatus.OK);
    		else
    		throw new UnknownException("Unknown Exception Occured");
    }
    
}