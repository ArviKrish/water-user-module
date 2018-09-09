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
import com.mongodb.QueryBuilder;
import com.water.user.springboot.config.Messages;
import com.water.user.springboot.constants.Constants;
import com.water.user.springboot.converters.CustomConverters;
import com.water.user.springboot.document.PotentialUsers;
import com.water.user.springboot.document.UserMobileNumbers;
import com.water.user.springboot.document.WahterUsers;

@Repository
public class PotentialUsersRepositoryImpl extends BaseRepository implements PotentialUsersRepositoryCustom {

	@Autowired
	Messages messages;

	public PotentialUsersRepositoryImpl(MongoDbFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter,
			CustomConverters customConverters, MongoOperations mongoOperations) {
		super(mongoDbFactory, mappingMongoConverter, customConverters, mongoOperations);
	}

	public boolean isPhoneNumberRegistered(String phoneNumber, String collectionName) throws Exception {

		DBObject query = QueryBuilder.start().put(Constants.PHONE_NUMBER).is(phoneNumber).get();
		return isAvailable(collectionName, query);
	}

	@Override
	public PotentialUsers getPotentialUserByPhoneNumber(String phoneNumber, String password) throws Exception {

		QueryBuilder queryBuilder = QueryBuilder.start();
		queryBuilder.and(QueryBuilder.start().put(Constants.PHONE_NUMBER).is(phoneNumber).get());
		if(password!=null)
			queryBuilder.and(QueryBuilder.start().put(Constants.PASSWORD).is(password).get());
		DBObject query =queryBuilder.get();
		DBObject potentialUserObject = findOneObject(Constants.COLLECTION_POTENTIAL_USERS, query);
		if (potentialUserObject != null) {
			return (PotentialUsers) convertRead(PotentialUsers.class, potentialUserObject);
		}
		return null;
	}
	
}
