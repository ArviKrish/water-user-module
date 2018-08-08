package com.water.user.springboot.converters;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomConverters {
	
	private LongToDateConverter longToDateConverter;
	private LocalDateWriteConverter localDateWriteConverter;
	private LocalDateReadConverter localDateReadConverter;
	
	List<Converter> converters;
	
	
	public CustomConverters()
	{
		longToDateConverter = new LongToDateConverter();
		localDateWriteConverter = new LocalDateWriteConverter();
		localDateReadConverter = new LocalDateReadConverter();
		converters = new ArrayList<Converter>();
		converters.add(localDateReadConverter);
		converters.add(localDateWriteConverter);
		converters.add(longToDateConverter);
	}
	
	public List<Converter> getAllConverters(){
		
		return converters;
		
	}
	
	public class LongToDateConverter implements Converter<Long, Date> {

		@Override
		public Date convert(Long longDate) {
			System.out.println("Called here");
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
