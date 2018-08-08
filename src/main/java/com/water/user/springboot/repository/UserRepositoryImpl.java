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
import com.water.user.springboot.util.MongoUtils;

public class UserRepositoryImpl extends RepositoryImpl  {
	
	public UserRepositoryImpl(MongoDbFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter,
			CustomConverters customConverters, MongoOperations mongoOperations) {
		super(mongoDbFactory, mappingMongoConverter, customConverters, mongoOperations);
	}

	@Autowired
	private MongoUtils mongoUtils;
	
	
    
    public Users insertUser(Users users) {
		

	    try {

	    	if(isPhoneNumberRegistered(users.getPhoneNumber())) {
	    		throw new PhoneNumberExistsException("");
	    	}
		DBCollection collection = mongoUtils.getDB().getCollection(Constants.COLLECTION_USERS);

		DBObject document = new BasicDBObject();
		//document = MongoUtils.getDbObject(users);

		
		/*BasicDBObject userswww =*/ mongoTemplate.getConverter().write(users, document);
		mongoOperations.insert(document, Constants.COLLECTION_USERS);
		
		
		/*collection.insert(document);*/
		
		DBObject query = new QueryBuilder().start().put("phoneNumber").is(users.getPhoneNumber()).get();
		
		/*DBCursor cursorDoc = collection.findOne(query);*/
		DBObject basicDBObject = collection.findOne(query);
		return mongoTemplate.getConverter().read(Users.class, basicDBObject); 
		
		/*while (cursorDoc.hasNext()) {
			DBObject basicDBObject = cursorDoc.next();
			Users foo = mongoTemplate.getConverter().read(Users.class, basicDBObject); 
			System.out.println("GGGG : "+foo.getEmailId()); 
		}*/
	    } catch (MongoException e) {
		e.printStackTrace();
	    }

	    return null;
	}
	
	private boolean isPhoneNumberRegistered(String phoneNumber) {
		
		DBCollection collection = mongoUtils.getDB().getCollection(Constants.COLLECTION_USERS);
		DBObject query = new QueryBuilder().start().put("phoneNumber").is(phoneNumber).get();
		DBObject basicDBObject = collection.findOne(query);
		if(basicDBObject == null) {
			return false;
		}
		return true;
	}
	
	
    public Users validateUserByPhoneNumber(String phoneNumber, String password) {
		
		
		
		DBCollection collection = mongoUtils.getDB().getCollection(Constants.COLLECTION_USERS);
		
		 DBObject query = new QueryBuilder().start().and(new QueryBuilder().start().put("phoneNumber").is(phoneNumber).get(),
				 new QueryBuilder().start().put("password").is(password).get()).get();
		 
		DBObject basicDBObject = collection.findOne(query);
		if(basicDBObject != null) {
		Users users = mongoTemplate.getConverter().read(Users.class, basicDBObject); 
		
		return users;
		}
		throw new LoginException("Login Failed - Incorrect Phone number or Password provided");
	}
    
    
    
    
}
