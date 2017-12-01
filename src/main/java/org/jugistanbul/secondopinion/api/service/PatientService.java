package org.jugistanbul.secondopinion.api.service;

import org.jugistanbul.secondopinion.api.dto.PatientInformation;
import org.jugistanbul.secondopinion.api.dto.PatientResponse;
import org.jugistanbul.secondopinion.api.entity.Patient;
import org.jugistanbul.secondopinion.api.repository.PatientRepository;
import org.jugistanbul.secondopinion.api.service.converter.PatientEntityToInformationConverter;
import org.jugistanbul.secondopinion.api.service.converter.PatientRequestToEntityConverter;
import org.jugistanbul.secondopinion.api.service.validator.PatientValidator;
import org.jugistanbul.secondopinion.api.utils.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

  private PatientEntityToInformationConverter patientEntityToInformationConverter;
  private PatientRequestToEntityConverter patientRequestToEntityConverter;
  private PatientRepository patientRepository;
  private PatientValidator patientValidator;

  public PatientService(
      PatientEntityToInformationConverter patientEntityToInformationConverter,
      PatientRequestToEntityConverter patientRequestToEntityConverter,
      PatientRepository patientRepository,
      PatientValidator patientValidator) {
    this.patientEntityToInformationConverter = patientEntityToInformationConverter;
    this.patientRequestToEntityConverter = patientRequestToEntityConverter;
    this.patientRepository = patientRepository;
    this.patientValidator = patientValidator;
  }

  public PatientResponse create(PatientInformation request) {
    patientValidator.validate(request);
    Patient patient = patientRequestToEntityConverter.convert(request);
    Patient patientPersisted = patientRepository.save(patient);
    PatientResponse patientResponse = new PatientResponse();
    patientResponse.setPatientId(patientPersisted.getId());

    return patientResponse;
  }

  public PatientInformation retrievePatient(Long id) {
    patientValidator.validate(id);
    Patient patient = patientRepository.findOne(id);

    return patientEntityToInformationConverter.convert(patient);
  }

  public void putPatient(Long id, PatientInformation request) {
    patientValidator.validate(id);
    // we can not call our current validate funtion here since there might be some parameters missing during update
    // patientValidator.validate(request);

    Patient patient = patientRepository.findOne(id);
    Patient newPatient = patientRequestToEntityConverter.convert(request);


    ObjectUtils.myCopyProperties(newPatient,patient);
    patientRepository.save(patient);

  }




}
