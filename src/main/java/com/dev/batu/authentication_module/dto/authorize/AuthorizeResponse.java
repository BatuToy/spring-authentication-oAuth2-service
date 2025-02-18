package com.dev.batu.authentication_module.dto.authorize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class AuthorizeResponse {

    private final UUID userId;
    private final String email;
    private final String role;
}
