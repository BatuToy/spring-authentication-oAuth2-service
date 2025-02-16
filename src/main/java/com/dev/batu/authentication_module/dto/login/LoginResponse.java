package com.dev.batu.authentication_module.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Getter
public class LoginResponse {
    @NotNull
    private final String token;
    @NotNull
    private String message;
}
