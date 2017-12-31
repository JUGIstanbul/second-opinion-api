package org.jugistanbul.secondopinion.api.exception;

public class InvalidLoginCredentialsException extends SecondOpinionPlatformException {

	public InvalidLoginCredentialsException(String message) {
		super(message);
	}

	public InvalidLoginCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}
}
