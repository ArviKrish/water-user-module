package com.water.user.springboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import com.water.user.springboot.converters.CustomConverters;

public class RepositoryImpl {

	MongoDbFactory mongoDbFactory;
    MongoTemplate mongoTemplate;
    MappingMongoConverter mappingMongoConverter;
	
    

    
	 @Autowired
	    public RepositoryImpl(MongoDbFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter, CustomConverters customConverters) {
	        this.mongoDbFactory = mongoDbFactory;
	        this.mongoTemplate = new MongoTemplate(mongoDbFactory, mappingMongoConverter);
	        System.out.println(customConverters);
	        mappingMongoConverter.setCustomConversions(new CustomConversions(customConverters.getAllConverters()));
	        mappingMongoConverter.afterPropertiesSet();
	        this.mappingMongoConverter = mappingMongoConverter;
	    }
	    
}
