package com.dev.batu.authentication_module.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class LoginCommand {
    @NotNull
    @NotBlank(message = "Email must not be blank!")
    @Email(message = "Email must be valid!")
    private final String email;

    @NotNull
    @NotBlank(message = "Password must not be blank!")
    private final String rawPassword;
}
