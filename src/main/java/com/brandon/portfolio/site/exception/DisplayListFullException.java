package com.brandon.portfolio.site.exception;

public class DisplayListFullException extends RuntimeException{

	public DisplayListFullException() {
		super();
	}

	public DisplayListFullException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DisplayListFullException(String message, Throwable cause) {
		super(message, cause);
	}

	public DisplayListFullException(String message) {
		super(message);
	}

	public DisplayListFullException(Throwable cause) {
		super(cause);
	}

}
