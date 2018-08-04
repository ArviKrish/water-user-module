package com.water.user.springboot.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.QueryBuilder;
import com.mongodb.util.JSON;
import com.water.user.springboot.constants.Constants;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.exceptions.LoginException;
import com.water.user.springboot.exceptions.PhoneNumberExistsException;
import com.water.user.springboot.util.MongoUtils;

	/**
	 * Java MongoDB : Insert a Document
	 * 
	 */
	@Repository
	public class UserDao {
		
		@Autowired
		private MongoUtils mongoUtils;
		
		private MongoTemplate mongoTemplate;
		
		private UserRepository userRepository;

		@Autowired
	    public UserDao(MongoTemplate mongoTemplate) {
		    this.mongoTemplate = mongoTemplate;
		}
		
		public Users inserUser(Users users) {
			

		    try {

		    	if(isPhoneNumberRegistered(users.getPhoneNumber())) {
		    		throw new PhoneNumberExistsException("");
		    	}
			DBCollection collection = mongoUtils.getDB().getCollection(Constants.COLLECTION_USERS);

			BasicDBObject document = new BasicDBObject();
			document = MongoUtils.getDbObject(users);

			collection.insert(document);
			
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
			throw new LoginException("Login Failed - Incorrect Phone number or Password");
		}
		
		
	}
	
