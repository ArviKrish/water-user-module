package com.water.user.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Phone number is already registered with the system")
	public class PhoneNumberExistsException extends RuntimeException {

		  public PhoneNumberExistsException(String exception) {
		    super(exception);
		  }

}
