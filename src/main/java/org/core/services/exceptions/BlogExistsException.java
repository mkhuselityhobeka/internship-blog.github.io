package org.core.services.exceptions;

@SuppressWarnings("serial")
public class BlogExistsException extends RuntimeException {

	public BlogExistsException(String message,Throwable cause){
		super(message,cause);
	}
	public BlogExistsException(Throwable cause){
		super(cause);
	}
	public BlogExistsException(){
		super();
	}
}
