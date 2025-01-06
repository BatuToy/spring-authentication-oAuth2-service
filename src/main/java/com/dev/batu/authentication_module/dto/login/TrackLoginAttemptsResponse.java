package com.dev.batu.authentication_module.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class TrackLoginAttemptsResponse {
    private final List<LoginAttemptResult> attempts;
    private String message;
}
