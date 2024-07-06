package com.herchanivska.viktoriia.bakingblog.exception;

public class WrongPasswordSubmitException extends RuntimeException {
    public WrongPasswordSubmitException(String message) {
        super(message);
    }
}