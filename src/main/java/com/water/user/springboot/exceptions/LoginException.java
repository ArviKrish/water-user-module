package com.water.user.springboot.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public class LoginException extends RuntimeException {

		  public LoginException(String exception) {
		    super(exception);
		  }

}
