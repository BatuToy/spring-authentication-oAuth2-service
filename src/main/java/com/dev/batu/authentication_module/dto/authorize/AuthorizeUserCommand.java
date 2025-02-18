package com.dev.batu.authentication_module.dto.authorize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
@Builder
@AllArgsConstructor
public class AuthorizeUserCommand {

    @Email(message = "Enter correct format!")
    private final String email;
}
