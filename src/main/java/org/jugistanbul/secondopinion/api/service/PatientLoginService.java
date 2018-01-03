package org.jugistanbul.secondopinion.api.service;

import org.jugistanbul.secondopinion.api.dto.PatientLoginInformation;
import org.jugistanbul.secondopinion.api.entity.Patient;
import org.jugistanbul.secondopinion.api.exception.InvalidLoginCredentialsException;
import org.jugistanbul.secondopinion.api.repository.PatientRepository;
import org.jugistanbul.secondopinion.api.service.validator.PatientLoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientLoginService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PatientLoginValidator patientLoginValidator;

	public void logInPatient(final PatientLoginInformation request) {

		patientLoginValidator.validate(request);

		final Patient patient = patientRepository.findByUsernameAndPassword(request.getUsername(),
				request.getPassword());

		if (patient == null)
			throw new InvalidLoginCredentialsException("invalid.credentials");

	}

}
