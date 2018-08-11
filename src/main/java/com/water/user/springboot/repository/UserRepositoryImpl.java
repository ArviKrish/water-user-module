package com.water.user.springboot.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.QueryBuilder;

import com.water.user.springboot.constants.Constants;
import com.water.user.springboot.converters.CustomConverters;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.exceptions.LoginException;
import com.water.user.springboot.exceptions.PhoneNumberExistsException;

public class UserRepositoryImpl extends RepositoryImpl  {
	
	public UserRepositoryImpl(MongoDbFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter,
			CustomConverters customConverters, MongoOperations mongoOperations) {
		super(mongoDbFactory, mappingMongoConverter, customConverters, mongoOperations);
	}

    
    public Users insertUser(Users users) throws Exception {
	    try {

	    	if(isPhoneNumberRegistered(users.getPhoneNumber())) {
	    		throw new PhoneNumberExistsException("Phone number is already registered with the system");
	    	}
		DBObject document = new BasicDBObject();
		convertWrite(users, document);
		insertObject(document);
		
		DBObject query = new QueryBuilder().start().put("phoneNumber").is(users.getPhoneNumber()).get();
		
		DBObject basicDBObject = findOneObject(Constants.COLLECTION_USERS,query);
		return (Users) convertRead(Users.class, basicDBObject);
	    } catch (MongoException e) {
		e.printStackTrace();
	    }

	    return null;
	}

	private boolean isPhoneNumberRegistered(String phoneNumber) throws Exception {
		
		DBObject query = new QueryBuilder().start().put("phoneNumber").is(phoneNumber).get();
		return isAvailable(Constants.COLLECTION_USERS, query);
	}

	
    public boolean validateUserByPhoneNumber(String phoneNumber, String password) throws Exception {

		DBObject query = new QueryBuilder().start()
				.and(new QueryBuilder().start().put("phoneNumber").is(phoneNumber).get(),
						new QueryBuilder().start().put("password").is(password).get()).get();

		DBObject basicDBObject = findOneObject(Constants.COLLECTION_USERS,query);
		if (basicDBObject != null) {
			return true;
		}
		throw new LoginException("Login Failed - Incorrect Phone number or Password provided");
	}


	public Users getUser(String phoneNumber) throws Exception {

		DBObject query = new QueryBuilder().start().put("phoneNumber").is(phoneNumber).get();
		
		DBObject basicDBObject = findOneObject(Constants.COLLECTION_USERS,query);
		if (basicDBObject == null) {
			throw new LoginException("User not found");
		}
		return (Users) convertRead(Users.class, basicDBObject);
	}
}
