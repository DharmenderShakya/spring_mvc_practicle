package com.srping_mvc_library_practicle.exception;

public class AuthorizationDeniedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AuthorizationDeniedException(String message) {
        super(message);
    }

	public AuthorizationDeniedException() {
		super();
	}
}
