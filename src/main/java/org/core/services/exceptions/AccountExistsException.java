package org.core.services.exceptions;



@SuppressWarnings("serial")
public class AccountExistsException extends RuntimeException{
	
	
	public AccountExistsException(String message, Throwable cause){
		super(message,cause);
	}
	public AccountExistsException(String message){
		super(message);
	}
	
	 public AccountExistsException() {
	    }

}
