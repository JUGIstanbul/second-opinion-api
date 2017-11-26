package org.jugistanbul.secondopinion.api.service;

import org.jugistanbul.secondopinion.api.dto.PatientRequest;
import org.jugistanbul.secondopinion.api.dto.PatientResponse;
import org.jugistanbul.secondopinion.api.entity.Patient;
import org.jugistanbul.secondopinion.api.repository.PatientRepository;
import org.jugistanbul.secondopinion.api.service.converter.PatientRequestToEntityConverter;
import org.jugistanbul.secondopinion.api.service.validator.PatientValidator;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private PatientValidator patientValidator;
    
    private PatientRepository patientRepository;
    
    private PatientRequestToEntityConverter patientConverter;

    public PatientService(PatientValidator patientValidator, PatientRepository patientRepository, PatientRequestToEntityConverter patientConverter) {
        this.patientValidator = patientValidator;
        this.patientRepository = patientRepository;
        this.patientConverter = patientConverter;
    }


    public PatientResponse create(PatientRequest request) {
        patientValidator.validate(request);
        
        Patient patient = patientConverter.apply(request);
        Patient patientPersisted = patientRepository.save(patient);

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setPatientId(patientPersisted.getId());

        return patientResponse;
    }
}
