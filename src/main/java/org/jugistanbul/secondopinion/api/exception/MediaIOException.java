package org.jugistanbul.secondopinion.api.exception;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class MediaIOException extends SecondOpinionPlatformException {

    public MediaIOException(String message) {
        super(message);
    }

    public MediaIOException(String message, Throwable cause) {
        super(message, cause);
    }
}
