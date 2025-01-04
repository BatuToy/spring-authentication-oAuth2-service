package com.dev.batu.authentication_module.exception;

import org.springframework.security.core.AuthenticationException;

public class DuplicationException extends AuthenticationException {
    public DuplicationException(String msg) {
        super(msg);
    }

    public DuplicationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
