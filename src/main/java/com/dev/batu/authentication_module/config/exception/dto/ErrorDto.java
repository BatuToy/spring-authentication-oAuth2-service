package com.dev.batu.authentication_module.config.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Getter
public class ErrorDto {

    @NotNull
    private final String code;
    private final String message;
}
