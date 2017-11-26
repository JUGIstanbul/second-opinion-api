package org.jugistanbul.secondopinion.api.service.validator;

import org.jugistanbul.secondopinion.api.dto.PatientRequest;
import org.jugistanbul.secondopinion.api.exception.EntityValidationException;
import org.springframework.stereotype.Component;

@Component
public class PatientValidator{

    public void validate(PatientRequest patientRequest) {
        if (patientRequest == null) {
            throw new EntityValidationException("request.missing");
        }

        if (patientRequest.getEmail() == null) {
            throw new EntityValidationException("email.required");
        }
    }
}
