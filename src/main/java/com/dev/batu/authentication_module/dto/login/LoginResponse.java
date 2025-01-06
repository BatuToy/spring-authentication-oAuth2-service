package com.dev.batu.authentication_module.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class LoginResponse {
    @NotNull(message = "Token must not be null!")
    private final String token;

    private String message;
}
