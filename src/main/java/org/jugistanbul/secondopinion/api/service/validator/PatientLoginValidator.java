package org.jugistanbul.secondopinion.api.service.validator;

import org.jugistanbul.secondopinion.api.dto.PatientLoginInformation;
import org.jugistanbul.secondopinion.api.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PatientLoginValidator {

	public void validate(PatientLoginInformation patientLoginInformation) {
		if (patientLoginInformation == null) {
			throw new EntityNotFoundException("request.missing");
		}

		if (patientLoginInformation.getUsername() == null) {
			throw new EntityNotFoundException("username.required");
		}

		if (patientLoginInformation.getPassword() == null) {
			throw new EntityNotFoundException("password.required");
		}
	}

}
