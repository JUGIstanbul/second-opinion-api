package org.jugistanbul.secondopinion.api.service.converter;

import java.util.function.Function;

import org.jugistanbul.secondopinion.api.dto.PatientInformation;
import org.jugistanbul.secondopinion.api.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientRequestToEntityConverter implements Function<PatientInformation,Patient> {

	@Override
	public Patient apply(PatientInformation patientInformation) {

		Patient patient = new Patient();
		patient.setEmail(patientInformation.getEmail());
		patient.setPassword(patientInformation.getPassword());
		patient.setUsername(patientInformation.getUsername());
		patient.setPhone(patientInformation.getPhone());
		
		return patient;
	}
}
