package com.water.user.springboot.exceptions;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.water.user.springboot.Responses.Response;
import com.water.user.springboot.constants.Constants;
import com.water.user.springboot.service.responsegenerator.ResponseGenerator;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
    @Autowired
    private ResponseGenerator responseGenerator;
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<Response> handleSQLException(HttpServletRequest request, Exception ex){
		logger.info("SQLException Occured:: URL="+request.getRequestURL());
		
		return responseGenerator.createErrorResponse(ex.getMessage(), Constants.ERROR_CODE_1000, HttpStatus.BAD_REQUEST, null);
		
		
	}
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IOException.class)
	public ResponseEntity<Response> handleIOException(HttpServletRequest request, Exception ex){
		return responseGenerator.createErrorResponse(ex.getMessage(), Constants.ERROR_CODE_1000, HttpStatus.BAD_REQUEST, null);
	}
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleException(HttpServletRequest request, Exception ex){
		return responseGenerator.createErrorResponse(ex.getMessage(), Constants.ERROR_CODE_1000, HttpStatus.BAD_REQUEST, null);
	}
	
}
