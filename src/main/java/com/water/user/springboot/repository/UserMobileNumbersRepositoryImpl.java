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
import com.water.user.springboot.document.PotentialUsers;
import com.water.user.springboot.document.UserMobileNumbers;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.document.WahterUsers;
import com.water.user.springboot.exceptions.LoginException;
import com.water.user.springboot.exceptions.PhoneNumberExistsException;
import com.water.user.springboot.exceptions.ValidationException;
import com.water.user.springboot.repository.WahterUsersRepositoryImpl;
import com.water.user.springboot.repository.UserMobileNumbersRepositoryCustom;

@Repository
public class UserMobileNumbersRepositoryImpl extends BaseRepository implements UserMobileNumbersRepositoryCustom {

	@Autowired
	Messages messages;
	@Autowired
	WahterUsersRepositoryImpl usersRepositoryImpl;

	public UserMobileNumbersRepositoryImpl(MongoDbFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter,
			CustomConverters customConverters, MongoOperations mongoOperations) {
		super(mongoDbFactory, mappingMongoConverter, customConverters, mongoOperations);
	}

	public boolean isPhoneNumberRegistered(String phoneNumber, String collectionName) throws Exception {

		DBObject query = new QueryBuilder().start().put(Constants.PHONE_NUMBER).is(phoneNumber).get();
		return isAvailable(collectionName, query);
	}
}
