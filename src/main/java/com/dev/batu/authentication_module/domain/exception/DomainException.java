package com.dev.batu.authentication_module.domain.exception;

public abstract class DomainException extends RuntimeException{
    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(String message) {
        super(message);
    }
}
