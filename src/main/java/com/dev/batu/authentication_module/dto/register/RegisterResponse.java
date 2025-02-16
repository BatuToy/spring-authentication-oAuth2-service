package com.dev.batu.authentication_module.dto.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Getter
public class RegisterResponse {
    @NotNull
    private final String userName;
    @NotNull
    private final String role;

    private final String message;
}
