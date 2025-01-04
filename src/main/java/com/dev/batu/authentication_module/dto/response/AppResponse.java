package com.dev.batu.authentication_module.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
public final class AppResponse<T> {
    private final T data;
    private final HttpStatus code;
    private final String message;
}
