package org.jugistanbul.secondopinion.api.exception;

public class InvalidUserNameException extends SecondOpinionPlatformException {

	public InvalidUserNameException(String message) {
		super(message);
	}

	public InvalidUserNameException(String message, Throwable cause) {
		super(message, cause);
	}
}
