package org.core.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class BlogNotFoundException extends RuntimeException{

	public BlogNotFoundException(String message, Throwable cause){
		super(message,cause);
	}
	public BlogNotFoundException(String message){
		super(message);
	}
	
	 public BlogNotFoundException() {
	    }
}