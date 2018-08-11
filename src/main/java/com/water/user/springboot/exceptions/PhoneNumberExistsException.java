package com.water.user.springboot.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public class PhoneNumberExistsException extends RuntimeException {

		  public PhoneNumberExistsException(String exception) {
		    super(exception);
		  }

}
