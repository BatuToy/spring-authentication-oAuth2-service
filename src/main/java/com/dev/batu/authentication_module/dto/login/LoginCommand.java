package com.dev.batu.authentication_module.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
            @Size(max = 22, message = "Password is too long!")
    })
    private final String rawPassword;
}
