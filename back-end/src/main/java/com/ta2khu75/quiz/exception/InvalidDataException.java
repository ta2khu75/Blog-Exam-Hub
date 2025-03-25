package com.ta2khu75.quiz.exception;

public class InvalidDataException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public InvalidDataException(String message) {
        super(message);
    }
}
