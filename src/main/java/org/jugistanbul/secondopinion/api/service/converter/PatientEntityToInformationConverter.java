package org.jugistanbul.secondopinion.api.service.converter;

import java.util.function.Function;
import org.jugistanbul.secondopinion.api.dto.PatientInformation;
import org.jugistanbul.secondopinion.api.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientEntityToInformationConverter implements Function<Patient,PatientInformation> {

	@Override
	public PatientInformation apply(Patient patient) {

		PatientInformation patientInformation = new PatientInformation();
		patientInformation.setEmail(patient.getEmail());
		patientInformation.setPassword(patient.getPassword());
		patientInformation.setUsername(patient.getUsername());
		patientInformation.setPhone(patient.getPhone());

		return patientInformation;
	}
}


