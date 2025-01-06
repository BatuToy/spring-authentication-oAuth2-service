package com.dev.batu.authentication_module.dto.login;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Builder
@Getter
public class LoginAttempt {
    @NotNull
    private final ZonedDateTime attemptAt;
    @NotNull
    private final Boolean isSuccess;
}
