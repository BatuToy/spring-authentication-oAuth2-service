package com.dev.batu.authentication_module.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class LoginCommand {
    @NotNull
    @Email
    private final String email;
    @NotNull
    private final String rawPassword;
}
