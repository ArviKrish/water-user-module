package com.water.user.springboot.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import com.water.user.springboot.constants.Constants;

import org.springframework.http.HttpStatus;


	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public class LoginException extends RuntimeException {
		
		String errorCode;

		  public String getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}

		public LoginException(String exception) {
		    super(Constants.ERROR_CODE_1100+"|"+exception);
		}
		  
		public LoginException(String exception, String errorCode) {
			super(errorCode+"|"+exception);
		}


}
