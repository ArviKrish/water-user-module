package com.water.user.springboot.validator;

import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequestParamValidator implements ConstraintValidator<RequestParammm, Map<String, String>> {

	
	
	@Override
	public void initialize(RequestParammm paramA) {
	}

	@Override
	public boolean isValid(Map<String, String> paramMap, ConstraintValidatorContext ctx) {
		
System.out.println("Validate here");

return true;
		
	}

}
