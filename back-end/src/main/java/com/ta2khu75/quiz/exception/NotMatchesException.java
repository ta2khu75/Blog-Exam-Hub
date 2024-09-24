package com.ta2khu75.quiz.exception;

public class NotMatchesException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public NotMatchesException(String message) {
        super(message);
    }
}
