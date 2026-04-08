package com.srping_mvc_json_view_practicle.exception;

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
