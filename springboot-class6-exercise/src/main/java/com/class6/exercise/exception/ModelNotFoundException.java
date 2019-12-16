package com.class6.exercise.exception;

public class ModelNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModelNotFoundException() {
		super();
	}
	
	public ModelNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ModelNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ModelNotFoundException(String message) {
		super(message);
	}

	public ModelNotFoundException(Throwable cause) {
		super(cause);
	}	
}
