package com.dev.batu.authentication_module.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Builder
@Getter
public class LoginAttemptResult {
    @NotNull
    private final ZonedDateTime attemptAt;

    @NotNull
    private final Boolean isSuccess;

    private final String excMessage;
}
