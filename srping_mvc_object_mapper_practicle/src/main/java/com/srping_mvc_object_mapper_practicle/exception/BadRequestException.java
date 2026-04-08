package com.srping_mvc_object_mapper_practicle.exception;

public class BadRequestException extends RuntimeException {

	String message;

	public BadRequestException(String message) {
		super();
		this.message = message;
	}

	public BadRequestException() {
		super();
	}

	public BadRequestException(Exception ex) {
		super(ex);
		this.message=ex.getMessage();
	}
	
	
	
}
