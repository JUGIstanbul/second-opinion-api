package org.jugistanbul.secondopinion.api.service;

import org.jugistanbul.secondopinion.api.dto.PatientRequest;
import org.jugistanbul.secondopinion.api.dto.PatientResponse;
import org.jugistanbul.secondopinion.api.service.validator.PatientValidator;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.StampedLock;

@Service
public class PatientService {

    private PatientValidator patientValidator;

    public PatientService(PatientValidator patientValidator) {
        this.patientValidator = patientValidator;
    }

    public PatientResponse create(PatientRequest request) {
        patientValidator.validate(request);

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setPatientId("10001");

        return patientResponse;
    }
}
