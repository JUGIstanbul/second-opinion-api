package org.jugistanbul.secondopinion.api.exception;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public abstract class SecondOpinionPlatformException extends RuntimeException {

    public SecondOpinionPlatformException(String message) {
        super(message);
    }

    public SecondOpinionPlatformException(String message, Throwable cause) {
        super(message, cause);
    }
}
