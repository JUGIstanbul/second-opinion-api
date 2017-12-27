package org.jugistanbul.secondopinion.api.service.converter;

import org.jugistanbul.secondopinion.api.dto.PatientInformation;
import org.jugistanbul.secondopinion.api.entity.Patient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PatientEntityToInformationConverter implements Converter<Patient, PatientInformation> {

  @Override
  public PatientInformation convert(Patient patient) {
    PatientInformation patientInformation = new PatientInformation();
    patientInformation.setEmail(patient.getEmail());
    patientInformation.setPassword(patient.getPassword());
    patientInformation.setUsername(patient.getUsername());
    patientInformation.setPhone(patient.getPhone());
    if (patient.getGender() != null) {
      patientInformation.setGender(patient.getGender().toString());
    }
    patientInformation.setAddress(patient.getAddress());
    patientInformation.setAddictiveDrugProfile(patient.getAddictiveDrugProfile());
    patientInformation.setAlcoholConsumptionProfile(patient.getAlcoholConsumptionProfile());
    patientInformation.setSmokerProfile(patient.getSmokerProfile());
    patientInformation.setJob(patient.getJob());
    patientInformation.setChronicDiseases(patient.getChronicDiseases());
    patientInformation.setMedications(patient.getMedications());
    patientInformation.setPastOperations(patient.getPastOperations());

    return patientInformation;
  }
}


