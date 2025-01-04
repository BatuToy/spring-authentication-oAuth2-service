package com.dev.batu.authentication_module.dto.register;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class RegisterResponse {
    @NotNull
    private final String userName;
    private final String message;
}
