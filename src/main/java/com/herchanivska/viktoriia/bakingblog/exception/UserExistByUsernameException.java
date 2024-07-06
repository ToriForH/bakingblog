package com.herchanivska.viktoriia.bakingblog.exception;

public class UserExistByUsernameException extends RuntimeException {
    public UserExistByUsernameException(String message) {
        super(message);
    }
}
