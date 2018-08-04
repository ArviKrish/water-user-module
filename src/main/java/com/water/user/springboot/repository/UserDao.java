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

		@Autowired
	    public UserDao(MongoTemplate mongoTemplate) {
		    this.mongoTemplate = mongoTemplate;
		}
		
		public Users inserUser(Users users) {
			

		    try {

		    	if(isPhoneNumberRegistered(users.getPhoneNumber())) {
		    		throw new PhoneNumberExistsException("dfdfs");
		    	}
			DBCollection collection = mongoUtils.getDB().getCollection(Constants.COLLECTION_USERS);

			BasicDBObject document = new BasicDBObject();
			document = MongoUtils.getDbObject(users);

			collection.insert(document);
			
			DBObject query = new QueryBuilder()
			         .start().put("phoneNumber").is(users.getPhoneNumber()).get();
			
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
			DBObject query = new QueryBuilder()
			         .start().put("phoneNumber").is(phoneNumber).get();
			DBObject basicDBObject = collection.findOne(query);
			if(basicDBObject == null) {
				return false;
			}
			return true;
		}
		
		
		public void doService() {
		

	    try {

		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("mongo");
		
		DBCollection collection = db.getCollection("dummyColl");

		// 1. BasicDBObject example
		System.out.println("BasicDBObject example...");
		BasicDBObject document = new BasicDBObject();
		document.put("database", "mkyongDB");
		document.put("table", "hosting");

		BasicDBObject documentDetail = new BasicDBObject();
		documentDetail.put("records", 99);
		documentDetail.put("index", "vps_index1");
		documentDetail.put("active", "true");
		document.put("detail", documentDetail);

		collection.insert(document);

		DBCursor cursorDoc = collection.find();
		while (cursorDoc.hasNext()) {
			DBObject basicDBObject = cursorDoc.next();
		}

		collection.remove(new BasicDBObject());

		// 2. BasicDBObjectBuilder example
		System.out.println("BasicDBObjectBuilder example...");
		BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start()
			.add("database", "mkyongDB")
	                .add("table", "hosting");

		BasicDBObjectBuilder documentBuilderDetail = BasicDBObjectBuilder.start()
	                .add("records", "99")
	                .add("index", "vps_index1")
			.add("active", "true");

		documentBuilder.add("detail", documentBuilderDetail.get());

		collection.insert(documentBuilder.get());

		DBCursor cursorDocBuilder = collection.find();
		while (cursorDocBuilder.hasNext()) {
			System.out.println(cursorDocBuilder.next());
		}

		collection.remove(new BasicDBObject());

		// 3. Map example
		System.out.println("Map example...");
		Map<String, Object> documentMap = new HashMap<String, Object>();
		documentMap.put("database", "mkyongDB");
		documentMap.put("table", "hosting");

		Map<String, Object> documentMapDetail = new HashMap<String, Object>();
		documentMapDetail.put("records", "99");
		documentMapDetail.put("index", "vps_index1");
		documentMapDetail.put("active", "true");

		documentMap.put("detail", documentMapDetail);

		collection.insert(new BasicDBObject(documentMap));

		DBCursor cursorDocMap = collection.find();
		while (cursorDocMap.hasNext()) {
			System.out.println(cursorDocMap.next());
		}

		collection.remove(new BasicDBObject());

		// 4. JSON parse example
		System.out.println("JSON parse example...");
				
		String json = "{'database' : 'mkyongDB','table' : 'hosting'," +
		  "'detail' : {'records' : 99, 'index' : 'vps_index1', 'active' : 'true'}}}";

		DBObject dbObject = (DBObject)JSON.parse(json);
				
		collection.insert(dbObject);

		DBCursor cursorDocJSON = collection.find();
		while (cursorDocJSON.hasNext()) {
			System.out.println(cursorDocJSON.next());
		}

		collection.remove(new BasicDBObject());
				
	    } catch (MongoException e11) {
		e11.printStackTrace();
	    }


}
	}
	
