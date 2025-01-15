package com.dev.batu.authentication_module.dto.register;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class RegisterCommand {

    @Size.List({
            @Size(min = 4, message = "Username is too short!"),
            @Size(max = 11, message = "Username is too long!")
    })
    @NotBlank(message = "User name must not be blank!")
    private final String userName;

    @Email(message = "Email must be valid!")
    @NotBlank(message = "Email must not be blank!")
    @NotNull(message = "Email is null!")
    private final String email;

    @Size.List({
            @Size(min = 8, message = "Password is too short!"),
            @Size(max = 22, message = "Password is too long!")
    })
    @NotBlank(message = "Password must not be blank!")
    private final String rawPassword;

    @NotNull
    private final Contact contact;
}
