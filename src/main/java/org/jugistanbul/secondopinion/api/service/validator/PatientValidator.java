package org.jugistanbul.secondopinion.api.service.validator;

import org.jugistanbul.secondopinion.api.dto.PatientInformation;
import org.jugistanbul.secondopinion.api.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PatientValidator{

    public void validate(PatientInformation patientInformation) {
        if (patientInformation == null) {
            throw new EntityNotFoundException("request.missing");
        }

        if (patientInformation.getEmail() == null) {
            throw new EntityNotFoundException("email.required");
        }
    }

    public void validate(Long id) {
        if (id == null) {
            throw new EntityNotFoundException("patientId.required");
        }
    }
}
