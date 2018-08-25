package com.water.user.springboot.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteResult;
import com.water.user.springboot.config.Messages;
import com.water.user.springboot.constants.Constants;
import com.water.user.springboot.converters.CustomConverters;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.exceptions.LoginException;
import com.water.user.springboot.exceptions.PhoneNumberExistsException;
import com.water.user.springboot.exceptions.ValidationException;

@Repository
public class UsersRepositoryImpl extends BaseRepository implements UsersRepositoryCustom  {
	
	 @Autowired
	    Messages messages;
	
	public UsersRepositoryImpl(MongoDbFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter,
			CustomConverters customConverters, MongoOperations mongoOperations) {
		super(mongoDbFactory, mappingMongoConverter, customConverters, mongoOperations); 
	} 

    @Override
    public void insertUser(Users users) throws Exception {

	    	if(isPhoneNumberRegistered(users.getPhoneNumber())) {
	    		throw new PhoneNumberExistsException(messages.get("phonenumer.already.registered"));
	    	}
		DBObject document = new BasicDBObject();
		convertWrite(users, document);
		insertObject(document);
		
		/*DBObject query = new QueryBuilder().start().put(Constants.PHONE_NUMBER).is(users.getPhoneNumber()).get();
		DBObject basicDBObject = findOneObject(Constants.COLLECTION_USERS,query);
		return (Users) convertRead(Users.class, basicDBObject);*/
	}
    
    @Override
    public boolean updateUser(Users users) throws Exception {
	    	Query findUser = new Query();
	    	findUser.addCriteria(Criteria.where(Constants.PHONE_NUMBER).is(users.getPhoneNumber()));
	    	findUser.fields().include(Constants.PASSWORD);

			Users selectedUser = (Users) findOneObject(findUser, Users.class);

			if (selectedUser == null) {
				throw new ValidationException(messages.get("user.not.found"));
			}
			
			Update updateUser = new Update();
			updateUser.set(Constants.PASSWORD, users.getPassword());

			return updateFirst(findUser, updateUser, Users.class);
	}

	private boolean isPhoneNumberRegistered(String phoneNumber) throws Exception {
		
		DBObject query = new QueryBuilder().start().put(Constants.PHONE_NUMBER).is(phoneNumber).get();
		return isAvailable(Constants.COLLECTION_USERS, query);
	}

	@Override
    public boolean validateUserByPhoneNumber(String phoneNumber, String password) throws Exception {

		DBObject query = new QueryBuilder().start()
				.and(new QueryBuilder().start().put(Constants.PHONE_NUMBER).is(phoneNumber).get(),
						new QueryBuilder().start().put(Constants.PASSWORD).is(password).get()).get();

		DBObject basicDBObject = findOneObject(Constants.COLLECTION_USERS,query);
		if (basicDBObject != null) {
			return true;
		}
		throw new LoginException(messages.get("login.fail.incorrect.credentials"));
	}

	@Override
	public Users getUser(String phoneNumber) throws Exception {

		DBObject query = new QueryBuilder().start().put(Constants.PHONE_NUMBER).is(phoneNumber).get();
		
		DBObject basicDBObject = findOneObject(Constants.COLLECTION_USERS,query);
		if (basicDBObject == null) {
			throw new ValidationException(messages.get("user.not.found"));
		}
		return (Users) convertRead(Users.class, basicDBObject);
	}
}
