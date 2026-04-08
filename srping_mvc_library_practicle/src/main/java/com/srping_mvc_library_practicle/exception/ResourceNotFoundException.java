package com.srping_mvc_library_practicle.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
        super(message);
    }

	public ResourceNotFoundException() {
		super();
	}
		
}
