package org.core.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
public class AccountDoesNotExistException extends RuntimeException{

	public AccountDoesNotExistException(String message, Throwable cause){
		super(message,cause);
	}
	public AccountDoesNotExistException(String message){
		super(message);
	}
	
	 public AccountDoesNotExistException() {
	    }
}
