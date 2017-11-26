package org.jugistanbul.secondopinion.api.exception;

public class EntityValidationException extends SecondOpinionPlatformException {

    public EntityValidationException(String message) {
        super(message);
    }

    public EntityValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
