package com.brandon.portfolio.site.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.brandon.portfolio.site.response.GameErrorResponse;


@ControllerAdvice
public class GameRestExceptionHandler {
	
	// Add an exception handler for GameNotFoundException
	@ExceptionHandler
	public ResponseEntity<GameErrorResponse> handleException(GameNotFoundException e) {
	
		// Create GameErrorResponse
		
		GameErrorResponse error = new GameErrorResponse(
			HttpStatus.NOT_FOUND.value(),
			e.getMessage(),
			System.currentTimeMillis()
		);
		
		// Return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	// Add another exception handler 
	@ExceptionHandler
	public ResponseEntity<GameErrorResponse> handleException(DuplicateGameException e) {
	
		// Create GameErrorResponse
		GameErrorResponse error = new GameErrorResponse(
			HttpStatus.BAD_REQUEST.value(),
			e.getMessage(),
			System.currentTimeMillis()
		);
		
		// Return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	// Add another exception handler 
	@ExceptionHandler
	public ResponseEntity<GameErrorResponse> handleException(DisplayListFullException e) {
	
		// Create GameErrorResponse
		GameErrorResponse error = new GameErrorResponse(
			HttpStatus.BAD_REQUEST.value(),
			e.getMessage(),
			System.currentTimeMillis()
		);
		
		// Return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	// Add another exception handler ... to catch any exception (catch all)
	@ExceptionHandler
	public ResponseEntity<GameErrorResponse> handleException(Exception e) {
	
		// Create GameErrorResponse
		GameErrorResponse error = new GameErrorResponse(
			HttpStatus.BAD_REQUEST.value(),
			// e.getMessage(),
			"Error: Bad Request. Please re-check the request and try again.",
			System.currentTimeMillis()
		);
		
		// Return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
}
