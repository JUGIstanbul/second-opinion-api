package org.jugistanbul.secondopinion.api.service.converter;

import java.util.function.Function;

import org.jugistanbul.secondopinion.api.dto.PatientRequest;
import org.jugistanbul.secondopinion.api.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientRequestToEntityConverter implements Function<PatientRequest, Patient> {

	@Override
	public Patient apply(PatientRequest patientRequest) {

		Patient patient = new Patient();
		patient.setEmail(patientRequest.getEmail());
		patient.setPassword(patientRequest.getPassword());
		patient.setUsername(patientRequest.getUsername());
		patient.setPhone(patientRequest.getPhone());
		
		return patient;
	}


	
}
