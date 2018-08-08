package com.water.user.springboot.temp;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.water.user.springboot.document.Users;

public class CreateJSON {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		Users obj = new Users();

		//Object to JSON in file
		mapper.writeValue(new File("f:\\file.json"), obj);

		//Object to JSON in String
		String jsonInString = mapper.writeValueAsString(obj);
	}

}
