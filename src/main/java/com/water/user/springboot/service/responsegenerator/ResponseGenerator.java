package com.water.user.springboot.service.responsegenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

import com.water.user.springboot.Responses.ErrorMessage;
import com.water.user.springboot.Responses.Response;
import com.water.user.springboot.constants.Constants;

@Component
public class ResponseGenerator {
	
	
	HttpHeaders responseHeaders = new HttpHeaders();
	
	public ResponseGenerator() {
		
		responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	}
	
	public ResponseEntity<Response> createErrorResponse(String message, String code, HttpStatus httpStatus, List<ObjectError> validationErrors){
		
    	List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
    	
    	Consumer<ObjectError> errorResponseCreator = objectError -> {
    		ErrorMessage errorMessage = new ErrorMessage();
    		errorMessage.setErrorCode(objectError.getCode());
    		errorMessage.setErrorMessage(objectError.getDefaultMessage());
    		errorMessage.setField(objectError.getObjectName());
    		errorMessages.add(errorMessage);
    	};
    	
    	if(validationErrors != null) {
    		validationErrors.forEach(errorResponseCreator);
    	} else {
    		ErrorMessage errorMessage = new ErrorMessage();
    		errorMessage.setErrorCode(Constants.ERROR_CODE_1000);
    		errorMessage.setErrorMessage(message);
    		errorMessages.add(errorMessage);
    	}
    	
		Response response = new Response();
		response.setErrorMessages(errorMessages);
		response.setMessage(message);
		response.setResponseCode(code);
		return new ResponseEntity<Response>(response, responseHeaders,httpStatus);
	}
	
	public ResponseEntity<Response> createResponse(Object body, String message, String code, HttpStatus httpStatus){
		
		Response response = new Response();
		response.setMessage(message);
		response.setResponseCode(code);
		response.setBody(body);
		return new ResponseEntity<Response>(response, responseHeaders,httpStatus);
		} 
	

}
