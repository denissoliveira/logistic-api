package com.logistic.services.exception;

public class DataIntegrityException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String message) {
		super(message);
	}

	/*
	 * Throwable cause - é a causa de uma coisa que aconteceu antes
	 */
	public DataIntegrityException(String message, Throwable cause) {
		super(message, cause);
	}

}
