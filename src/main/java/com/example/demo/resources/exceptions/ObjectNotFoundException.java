package com.example.demo.resources.exceptions;

@SuppressWarnings("serial")
public class ObjectNotFoundException extends RuntimeException {

	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg,Throwable cause) {
		super(msg,cause);
	}
}
