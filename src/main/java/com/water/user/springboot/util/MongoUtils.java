package com.water.user.springboot.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.water.user.springboot.config.Configurations;

import de.undercouch.bson4jackson.BsonFactory;
import de.undercouch.bson4jackson.BsonParser;
import org.bson.BSON;
import org.bson.BSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class MongoUtils {

    private static ObjectMapper mapper;
    
    @Autowired
    private  Configurations configurations;

    static {
        BsonFactory bsonFactory = new BsonFactory();
        bsonFactory.enable(BsonParser.Feature.HONOR_DOCUMENT_LENGTH);
        mapper = new ObjectMapper(bsonFactory);
    }

    public static BasicDBObject getDbObject(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            mapper.writeValue(baos, object);

            BSONObject decode = BSON.decode(baos.toByteArray());
            return new BasicDBObject(decode.toMap());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public  DB getDB() {
    	
    	Mongo mongo = new Mongo(configurations.getDbProps().getHost(), Integer.parseInt(configurations.getDbProps().getPort()));
		return mongo.getDB(configurations.getDbProps().getName());
    }
}