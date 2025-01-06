package com.dev.batu.authentication_module.dto.register;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class RegisterCommand {

    // Todo: @NotBlank is not working!

    @Size.List({
            @Size(min = 4, message = "{validation.too-short}"),
            @Size(max = 11, message = "{validation.too-long}")
    })
    @NotBlank(message = "User name must not be blank!")
    private final String userName;

    @NotBlank(message = "Email must not be blank!")
    @Email(message = "Email must be valid!")
    private final String email;

    @Size.List({
            @Size(min = 8, message = "{validation.too-short}"),
            @Size(max = 22, message = "{validation.too-long}")
    })
    @NotBlank(message = "Password must not be blank!")
    private final String rawPassword;
}
