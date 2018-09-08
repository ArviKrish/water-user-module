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
import com.water.user.springboot.document.UserMobileNumbers;
import com.water.user.springboot.document.WahterUsers;
import com.water.user.springboot.exceptions.UnknownException;
import com.water.user.springboot.exceptions.ValidationException;
import com.water.user.springboot.service.UserMobileNumbersService;
import com.water.user.springboot.service.responsegenerator.ResponseGenerator;
import com.water.user.springboot.util.StringUtils;

import java.util.Map;
import java.util.Properties;

import javax.validation.Valid;

@RestController
@RequestMapping("/usermobilenumber")
public class UserMobileNumberResource {

	
    @Autowired
    private UserMobileNumbersService userMobileNumbersService;
    @Autowired
    private Configurations configurations;
    @Autowired
    private ResponseGenerator responseGenerator;
    @Autowired
    Messages messages;
    
    @Autowired
    @Qualifier("myProperties")
    private Properties myProperties;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response> createUserMobileNumber( @Valid  @RequestBody UserMobileNumbers userMobileNumbers, BindingResult bindingResult) throws Exception {
    	
    		if (bindingResult.hasErrors()) {
    		return responseGenerator.createErrorResponse(messages.get("validation.error"), Constants.ERROR_CODE_1000, HttpStatus.BAD_REQUEST, bindingResult.getAllErrors());	
    		}
    		userMobileNumbersService.insertUserMobileNumbers(userMobileNumbers);
			return responseGenerator.createResponse(null, "Thanks for your interest! We'll contact you shortly", Constants.RESPONSE_CODE_001, HttpStatus.OK);
    }
}
