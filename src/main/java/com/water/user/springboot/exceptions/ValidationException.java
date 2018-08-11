package com.water.user.springboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public class ValidationException extends RuntimeException {

		  public ValidationException(String exception) {
		    super(exception);
		  }
}
