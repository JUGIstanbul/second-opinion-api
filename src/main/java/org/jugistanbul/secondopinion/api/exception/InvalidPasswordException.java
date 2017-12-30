package org.jugistanbul.secondopinion.api.exception;

public class InvalidPasswordException extends SecondOpinionPlatformException {

	public InvalidPasswordException(String message) {
		super(message);
	}

	public InvalidPasswordException(String message, Throwable cause) {
		super(message, cause);
	}
}
