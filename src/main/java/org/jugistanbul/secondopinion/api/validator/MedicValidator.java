package org.jugistanbul.secondopinion.api.validator;

import org.jugistanbul.secondopinion.api.entity.Medic;
import org.jugistanbul.secondopinion.api.service.MedicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MedicValidator implements Validator {

	@Autowired
	private MedicService medicService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Medic.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.username", "Username is required.");		
	}

	

}
