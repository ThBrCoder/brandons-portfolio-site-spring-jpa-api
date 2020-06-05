package com.brandon.portfolio.site.exception;

public class GameNotFoundException extends RuntimeException{

	public GameNotFoundException() {
		super();
	}

	public GameNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GameNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public GameNotFoundException(String message) {
		super(message);
	}

	public GameNotFoundException(Throwable cause) {
		super(cause);
	}

}
