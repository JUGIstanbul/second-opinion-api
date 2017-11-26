package org.jugistanbul.secondopinion.api.exception;

public class PatientValidationException extends RuntimeException {

    public PatientValidationException(String message) {
        super(message);
    }

    public PatientValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
