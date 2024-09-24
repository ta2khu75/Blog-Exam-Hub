package com.ta2khu75.quiz.exception;

public class InValidDataException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public InValidDataException(String message) {
        super(message);
    }
}
