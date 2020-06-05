package com.brandon.portfolio.site.exception;

public class DuplicateGameException extends RuntimeException{

	public DuplicateGameException() {
		super();
	}

	public DuplicateGameException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DuplicateGameException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateGameException(String message) {
		super(message);
	}

	public DuplicateGameException(Throwable cause) {
		super(cause);
	}

}
