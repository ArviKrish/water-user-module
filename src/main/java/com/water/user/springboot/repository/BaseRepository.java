package com.water.user.springboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.water.user.springboot.constants.Constants;
import com.water.user.springboot.converters.CustomConverters;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.util.MongoUtils;

public class BaseRepository {

	protected MongoDbFactory mongoDbFactory;
	protected MongoTemplate mongoTemplate;
    protected MappingMongoConverter mappingMongoConverter;
    protected MongoOperations mongoOperations;
    
    @Autowired
    protected MongoUtils mongoUtils;
    
	 @Autowired
	    public BaseRepository(MongoDbFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter, CustomConverters customConverters, MongoOperations mongoOperations) {
	        this.mongoDbFactory = mongoDbFactory;
	        this.mongoTemplate = new MongoTemplate(mongoDbFactory, mappingMongoConverter);
	        mappingMongoConverter.setCustomConversions(new CustomConversions(customConverters.getAllConverters()));
	        mappingMongoConverter.afterPropertiesSet();
	        this.mappingMongoConverter = mappingMongoConverter;
	        this.mongoOperations = mongoOperations;
	    }
	 
	 protected boolean isAvailable(String collectionName, DBObject query) throws Exception {
			return (mongoOperations.getCollection(collectionName).count(query) == 0) ? false : true;
		}
	 
	 protected DBObject findOneObject(String collectionName ,DBObject query) throws Exception {
			return mongoOperations.getCollection(collectionName).findOne(query);
		}
	 
	 protected void insertObject(DBObject document, String collectionName) {
			mongoOperations.insert(document, collectionName);
		}
	 
	 protected Object findOneObject(Query query, Class clazz) {
		 	return mongoOperations.findOne(query, clazz);
		}
	 
	 protected boolean updateFirst(Query query, Update update, Class clazz) {
		 WriteResult writeResult =  mongoOperations.updateFirst(query, update, clazz);
		 return writeResult.isUpdateOfExisting();
		}
	 
	 protected Object convertRead(Class clazz, DBObject basicDBObject) {
			Object users = mongoTemplate.getConverter().read(clazz, basicDBObject);
			return users;
		}
	 
	 protected void convertWrite(Object object, DBObject document) {
			mongoTemplate.getConverter().write(object, document);
		}
	    
}
