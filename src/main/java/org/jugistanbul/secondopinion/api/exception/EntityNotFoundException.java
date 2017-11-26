package org.jugistanbul.secondopinion.api.exception;

public class EntityNotFoundException extends SecondOpinionPlatformException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}