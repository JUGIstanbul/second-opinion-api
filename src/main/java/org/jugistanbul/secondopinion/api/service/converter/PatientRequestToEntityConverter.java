package org.jugistanbul.secondopinion.api.service.converter;


import org.jugistanbul.secondopinion.api.dto.PatientInformation;
import org.jugistanbul.secondopinion.api.entity.Patient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PatientRequestToEntityConverter implements Converter<PatientInformation, Patient> {

  @Override
  public Patient convert(PatientInformation patientInformation) {

    Patient patient = new Patient();
    patient.setEmail(patientInformation.getEmail());
    patient.setPassword(patientInformation.getPassword());
    patient.setUsername(patientInformation.getUsername());
    patient.setPhone(patientInformation.getPhone());
    patient.setGender(patientInformation.getGender());
    patient.setAddress(patientInformation.getAddress());

    return patient;
  }


}
