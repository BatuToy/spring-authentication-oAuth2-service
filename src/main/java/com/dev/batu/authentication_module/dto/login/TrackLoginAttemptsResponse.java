package com.dev.batu.authentication_module.dto.login;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Builder
public class LoginAttemptResponse {
    @NotNull
    private final ZonedDateTime attemptAt;
    private final Boolean isSuccess;
}
