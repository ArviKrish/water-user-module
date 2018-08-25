package com.water.user.springboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public class UnknownException extends RuntimeException {

		  public UnknownException(String exception) {
		    super(exception);
		  }
}
