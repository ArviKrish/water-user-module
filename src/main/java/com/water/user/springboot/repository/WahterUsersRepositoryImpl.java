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
public class WahterUsersRepositoryImpl extends BaseRepository implements WahterUsersRepositoryCustom {

	@Autowired
	Messages messages;

	public WahterUsersRepositoryImpl(MongoDbFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter,
			CustomConverters customConverters, MongoOperations mongoOperations) {
		super(mongoDbFactory, mappingMongoConverter, customConverters, mongoOperations);
	}

	@Override
	public boolean updateWahterUser(WahterUsers wahterUsers) throws Exception {
		Query findUser = new Query();
		findUser.addCriteria(Criteria.where(Constants.PHONE_NUMBER).is(wahterUsers.getPhoneNumber()));
		findUser.fields().include(Constants.PASSWORD);
		WahterUsers selectedUser = (WahterUsers) findOneObject(findUser, WahterUsers.class);
		if (selectedUser == null) {
			return false;
		}
		Update updateUser = new Update();
		updateUser.set(Constants.PASSWORD, wahterUsers.getPassword());

		return updateFirst(findUser, updateUser, WahterUsers.class);

	}

	public boolean isPhoneNumberRegistered(String phoneNumber, String collectionName) throws Exception {

		DBObject query = QueryBuilder.start().put(Constants.PHONE_NUMBER).is(phoneNumber).get();
		return isAvailable(collectionName, query);
	}

	@Override
	public WahterUsers getWahterUserByPhoneNumber(String phoneNumber, String password) throws Exception {

		QueryBuilder queryBuilder = QueryBuilder.start();
		queryBuilder.and(QueryBuilder.start().put(Constants.PHONE_NUMBER).is(phoneNumber).get());
		if(password!=null)
			queryBuilder.and(QueryBuilder.start().put(Constants.PASSWORD).is(password).get());
		DBObject query =queryBuilder.get();
		DBObject wahterUserObject = findOneObject(Constants.COLLECTION_WAHTER_USERS, query);
		if (wahterUserObject != null) {
			return (WahterUsers) convertRead(WahterUsers.class, wahterUserObject);
		}
		return null;
	}
	
}
