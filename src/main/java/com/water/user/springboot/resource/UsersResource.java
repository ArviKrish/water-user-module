package com.water.user.springboot.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.water.user.springboot.config.Configurations;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.exceptions.LoginException;
import com.water.user.springboot.repository.UserDao;
import com.water.user.springboot.repository.UserRepository;
import com.water.user.springboot.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersResource {

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
    public Users authenticateUser(@RequestParam Map<String, String> queryMap) {
    	
    	if(queryMap.get("phoneNumber").equalsIgnoreCase("")||queryMap.get("password").equalsIgnoreCase(""))
    		throw new LoginException("Phone number and Password cannot be empty");
        return userService.authenticateUser(queryMap.get("phoneNumber"), queryMap.get("password"));
    }
    
    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public Users createUser( @Valid  @RequestBody Users users) {
    	
    	return userService.insertUser(users);

    }
    
}