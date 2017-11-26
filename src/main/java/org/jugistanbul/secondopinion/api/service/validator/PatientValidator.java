package org.jugistanbul.secondopinion.api.service.validator;

import org.jugistanbul.secondopinion.api.dto.PatientRequest;
import org.jugistanbul.secondopinion.api.exception.PatientValidationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PatientValidator{

    public void validate(PatientRequest patientRequest) {
        if (patientRequest == null) {
            throw new PatientValidationException("request.missing");
        }

        if (patientRequest.getEmail() == null) {
            throw new PatientValidationException("email.required");
        }
    }
}
