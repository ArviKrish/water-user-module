package com.water.user.springboot.exceptions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.water.user.springboot.Responses.Response;
import com.water.user.springboot.config.Messages;
import com.water.user.springboot.constants.Constants;
import com.water.user.springboot.service.responsegenerator.ResponseGenerator;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
    @Autowired
    private ResponseGenerator responseGenerator;
    @Autowired
    Messages messages;
	
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
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Response> handleValidationException(HttpServletRequest request, Exception ex){
		return responseGenerator.createErrorResponse(ex.getMessage(), Constants.ERROR_CODE_1000, HttpStatus.BAD_REQUEST, null);
	}
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UnknownException.class)
	public ResponseEntity<Response> handleUnknownException(HttpServletRequest request, Exception ex){
		return responseGenerator.createErrorResponse(ex.getMessage(), Constants.ERROR_CODE_1001, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PhoneNumberExistsException.class)
	public ResponseEntity<Response> handlePhoneNumberExistsException(HttpServletRequest request, Exception ex){
		return responseGenerator.createErrorResponse(ex.getMessage(), Constants.ERROR_CODE_1000, HttpStatus.BAD_REQUEST, null);
	}
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		return responseGenerator.createErrorResponse(messages.get("validation.error"), Constants.ERROR_CODE_1000, HttpStatus.BAD_REQUEST, ex.getBindingResult().getAllErrors());
	}
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<Response> handleLoginException(LoginException ex){
		return responseGenerator.createErrorResponse(ex.getMessage().split(Pattern.quote(Constants.PIPE_DELIMITER))[1], ex.getMessage().split(Pattern.quote(Constants.PIPE_DELIMITER))[0], HttpStatus.BAD_REQUEST, null);
	}
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleException(HttpServletRequest request, Exception ex){
		return responseGenerator.createErrorResponse(messages.get("internal.server.error"), Constants.ERROR_CODE_1002, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
	
}
