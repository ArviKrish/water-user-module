package com.water.user.springboot.converters;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.water.user.springboot.document.Address;
import com.water.user.springboot.document.Users;
import com.water.user.springboot.util.MongoUtils;

@Component
public class CustomConverters {
	
	private LongToDateConverter longToDateConverter;
	private LocalDateWriteConverter localDateWriteConverter;
	private LocalDateReadConverter localDateReadConverter;
	private UsersReaderConverter usersReaderConverter;
	private UsersWriterConverter usersWriterConverter;
	
	List<Converter> converters;
	
	@Autowired
	private MongoUtils mongoUtils;
	
	public CustomConverters()
	{
		longToDateConverter = new LongToDateConverter();
		localDateWriteConverter = new LocalDateWriteConverter();
		localDateReadConverter = new LocalDateReadConverter();
		usersReaderConverter = new UsersReaderConverter();
		usersWriterConverter = new UsersWriterConverter();
		converters = new ArrayList<Converter>();
		converters.add(localDateReadConverter);
		converters.add(localDateWriteConverter);
		converters.add(longToDateConverter);
		converters.add(usersWriterConverter);
		//converters.add(usersReaderConverter);
		
	}
	
	public List<Converter> getAllConverters(){
		
		return converters;
	}
	
	
	@Component
	public class UsersWriterConverter implements Converter<Users, DBObject> {
	    @Override
	    public DBObject convert(Users users) {
	        BasicDBObject document = new BasicDBObject();
			document = MongoUtils.getDbObject(users);
	        return document;
	    }
	}
	
	@Component
	public class UsersReaderConverter implements Converter<DBObject, Users> {
	    @Override
	    public Users convert(DBObject dbObject) {
	    	Users users = new Users();
	    	users.setGSTNumber(dbObject.get("gstnumber").toString());
	        if (dbObject.get("address") != null) {
	        	Address address = new Address();
	            DBObject emailDbObject = new BasicDBObject();
	            emailDbObject = (DBObject) dbObject.get("address");
	            address.setAddressLine1((emailDbObject.get("addressLine1").toString()));
	            address.setAddressLine2((emailDbObject.get("addressLine2").toString()));
	            users.setAddress(address);
	        }
	       /* dbObject.removeField("_class");*/
	        return users;
	    }
	}
	
	
	public class LongToDateConverter implements Converter<Long, Date> {

		@Override
		public Date convert(Long longDate) {
	        if(longDate!=null)
	            return new Date(longDate);
	        else
	            return null;
	        }
	}
	
	public class LocalDateReadConverter implements Converter<String, LocalDateTime> {

		@Override
		public LocalDateTime convert(String arg0) {
			return null;
		}

	}
	
	public class LocalDateWriteConverter implements Converter<String, LocalDateTime> {

		@Override
		public LocalDateTime convert(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

	}

}
