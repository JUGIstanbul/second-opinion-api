package org.jugistanbul.secondopinion.api.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class EntityValidationException extends Exception {

    private List<ValidationExceptionDetail> errors = new ArrayList<>();

    public EntityValidationException() {
        super();
    }

    public EntityValidationException(String field, String message) {
        this();
        ValidationExceptionDetail error = new ValidationExceptionDetail();
        error.setField(field);
        error.setMessage(message);
        errors.add(error);
    }

    public EntityValidationException addFieldError(String field, String message) {

        ValidationExceptionDetail error = new ValidationExceptionDetail();
        error.setField(field);
        error.setMessage(message);
        errors.add(error);

        return this;
    }

    public void setErrors(List<ValidationExceptionDetail> errors) {
        this.errors = errors;
    }

    public List<ValidationExceptionDetail> getErrors() {
        return errors;
    }
}
