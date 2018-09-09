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
import com.water.user.springboot.repository.UsersRepositoryImpl;
import com.water.user.springboot.repository.UserMobileNumbersRepositoryCustom;

@Repository
public class UserMobileNumbersRepositoryImpl extends BaseRepository implements UserMobileNumbersRepositoryCustom {

	@Autowired
	Messages messages;
	@Autowired
	UsersRepositoryImpl usersRepositoryImpl;

	public UserMobileNumbersRepositoryImpl(MongoDbFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter,
			CustomConverters customConverters, MongoOperations mongoOperations) {
		super(mongoDbFactory, mappingMongoConverter, customConverters, mongoOperations);
	}

	@Override
	public void insertUserMobileNumbers(UserMobileNumbers userMobileNumbers) throws Exception {

		//if (usersRepositoryImpl.validatePhoneNumberForSignUp(userMobileNumbers.getPhoneNumber())) {
			DBObject query = new QueryBuilder().start().and(
					new QueryBuilder().start().put(Constants.PHONE_NUMBER).is(userMobileNumbers.getPhoneNumber()).get())
					.get();

			DBObject userMobileObject = findOneObject(Constants.COLLECTION_USER_MOBILE_NUMBERS, query);
			UserMobileNumbers userMobileNumbersResult = (UserMobileNumbers) convertRead(UserMobileNumbers.class,
					userMobileObject);
			if (userMobileNumbersResult == null) {
				DBObject document = new BasicDBObject();
				convertWrite(userMobileNumbers, document);
				insertObject(document, Constants.COLLECTION_USER_MOBILE_NUMBERS);
			} else {
				throw new LoginException("Your contact number is recorded. Our team will reach you shortly", Constants.ERROR_CODE_1107);
			}
		//}
		/*
		 * DBObject query = new
		 * QueryBuilder().start().put(Constants.PHONE_NUMBER).is(users.getPhoneNumber())
		 * .get(); DBObject basicDBObject =
		 * findOneObject(Constants.COLLECTION_USERS,query); return (Users)
		 * convertRead(Users.class, basicDBObject);
		 */
	}

	private boolean isPhoneNumberRegistered(String phoneNumber, String collectionName) throws Exception {

		DBObject query = new QueryBuilder().start().put(Constants.PHONE_NUMBER).is(phoneNumber).get();
		return isAvailable(collectionName, query);
	}

	/*
	 * @Override public boolean updateUser(UserMobileNumbers users) throws Exception
	 * { Query findUser = new Query();
	 * findUser.addCriteria(Criteria.where(Constants.PHONE_NUMBER).is(users.
	 * getPhoneNumber())); findUser.fields().include(Constants.PASSWORD);
	 * 
	 * Users selectedUser = (Users) findOneObject(findUser, Users.class);
	 * 
	 * if (selectedUser == null) { throw new
	 * ValidationException(messages.get("user.not.found")); }
	 * 
	 * Update updateUser = new Update(); updateUser.set(Constants.PASSWORD,
	 * users.getPassword());
	 * 
	 * return updateFirst(findUser, updateUser, Users.class); }
	 * 
	 * private boolean isPhoneNumberRegistered(String phoneNumber, String
	 * collectionName) throws Exception {
	 * 
	 * DBObject query = new
	 * QueryBuilder().start().put(Constants.PHONE_NUMBER).is(phoneNumber).get();
	 * return isAvailable(collectionName, query); }
	 * 
	 * @Override public boolean validateUserByPhoneNumber(String phoneNumber, String
	 * password) throws Exception {
	 * 
	 * DBObject query = new QueryBuilder().start() .and(new
	 * QueryBuilder().start().put(Constants.PHONE_NUMBER).is(phoneNumber).get(), new
	 * QueryBuilder().start().put(Constants.PASSWORD).is(password).get()).get();
	 * 
	 * DBObject wahterUserObject =
	 * findOneObject(Constants.COLLECTION_WAHTER_USERS,query); Users user=(Users)
	 * convertRead(Users.class, wahterUserObject); if (wahterUserObject != null) {
	 * if(user.getActive()) { return true; } else { throw new
	 * LoginException("We will contact you shortly"); } } DBObject
	 * potentialUserObject =
	 * findOneObject(Constants.COLLECTION_POTENTIAL_USERS,query); if
	 * (potentialUserObject != null) { throw new
	 * LoginException("Your registration is being processed..."); }
	 * 
	 * throw new LoginException(messages.get("login.fail.incorrect.credentials")); }
	 * 
	 * @Override public boolean validatePhoneNumber(String phoneNumber) throws
	 * Exception {
	 * 
	 * DBObject query = new QueryBuilder().start() .and(new
	 * QueryBuilder().start().put(Constants.PHONE_NUMBER).is(phoneNumber).get()).get
	 * ();
	 * 
	 * DBObject wahterUserObject =
	 * findOneObject(Constants.COLLECTION_WAHTER_USERS,query); if (wahterUserObject
	 * != null) { throw new LoginException("Phone number is already registered...");
	 * } DBObject potentialUserObject =
	 * findOneObject(Constants.COLLECTION_POTENTIAL_USERS,query); if
	 * (potentialUserObject != null) { throw new
	 * LoginException("Phone number is already registered... Registration is being processed..."
	 * ); }
	 * 
	 * return true; }
	 * 
	 * @Override public UserMobileNumbers getUser(String phoneNumber) throws
	 * Exception {
	 * 
	 * DBObject query = new
	 * QueryBuilder().start().put(Constants.PHONE_NUMBER).is(phoneNumber).get();
	 * 
	 * DBObject basicDBObject =
	 * findOneObject(Constants.COLLECTION_WAHTER_USERS,query); if (basicDBObject ==
	 * null) { throw new ValidationException(messages.get("user.not.found")); }
	 * return (Users) convertRead(Users.class, basicDBObject); }
	 * 
	 * @Override public void insertUserMobileNumbers(UserMobileNumbers
	 * userMobileNumbers) throws Exception { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public UserMobileNumbers getUser(String phoneNumber) throws
	 * Exception { // TODO Auto-generated method stub return null; }
	 * 
	 * @Override public boolean updateUser(UserMobileNumbers users) throws Exception
	 * { // TODO Auto-generated method stub return false; }
	 */
}
