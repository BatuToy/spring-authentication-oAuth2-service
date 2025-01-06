package com.dev.batu.authentication_module.config.exception.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ErrorDto {
    @NotNull
    private final String code;
    private final String message;
}
