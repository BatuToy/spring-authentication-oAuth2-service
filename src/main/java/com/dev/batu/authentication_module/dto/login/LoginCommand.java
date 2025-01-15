package com.dev.batu.authentication_module.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size.List({
            @Size(min = 8, message = "Password is too short!"),
            @Size(max = 8, message = "Password is too long!")
    })
    private final String rawPassword;
}
