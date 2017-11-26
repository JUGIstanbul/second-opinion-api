package org.jugistanbul.secondopinion.api.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException() {
        super();
    }
}