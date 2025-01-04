package com.dev.batu.authentication_module.dto.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class RegisterCommand {
    @NotNull
    private final String userName;
    @NotNull
    @Email
    private final String email;
    @NotNull
    private final String rawPassword;
    //additional fields related to user can come here!
}
