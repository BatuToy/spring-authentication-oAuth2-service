package com.dev.batu.authentication_module.exception;

public class NotFountException extends RuntimeException{
    public NotFountException(String message) {
        super(message);
    }

    public NotFountException(String message, Throwable cause) {
        super(message, cause);
    }
}
