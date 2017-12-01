package org.jugistanbul.secondopinion.api.service.converter;

import java.util.function.Function;
import org.jugistanbul.secondopinion.api.dto.PatientInformation;
import org.jugistanbul.secondopinion.api.entity.Patient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PatientEntityToInformationConverter implements Converter<Patient,PatientInformation> {

	@Override
	public PatientInformation convert(Patient patient) {
		PatientInformation patientInformation = new PatientInformation();
		patientInformation.setEmail(patient.getEmail());
		patientInformation.setPassword(patient.getPassword());
		patientInformation.setUsername(patient.getUsername());
		patientInformation.setPhone(patient.getPhone());
		patientInformation.setGender(patient.getGender());
		patientInformation.setAddress(patient.getAddress());


		return patientInformation;
	}
}


