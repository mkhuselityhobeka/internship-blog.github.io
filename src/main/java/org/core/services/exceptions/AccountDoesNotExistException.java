package org.core.services.exceptions;

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
