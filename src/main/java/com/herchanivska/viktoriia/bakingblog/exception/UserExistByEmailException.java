package com.herchanivska.viktoriia.bakingblog.exception;

public class UserExistByEmailException extends RuntimeException {
    public UserExistByEmailException(String message) {
        super(message);
    }
}
