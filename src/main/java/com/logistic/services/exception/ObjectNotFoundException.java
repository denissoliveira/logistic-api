package com.logistic.services.exception;

public class ObjectNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String message) {
		super(message);
	}

	/*
	 * Throwable cause - é a causa de uma coisa que aconteceu antes
	 */
	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
