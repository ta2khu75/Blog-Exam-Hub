package com.ta2khu75.quiz.exception;

public class UnAuthenticationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public UnAuthenticationException(String message) {
		super(message);
	}
}