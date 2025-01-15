package com.dev.batu.authentication_module.domain.entity;

import com.dev.batu.authentication_module.common.AggregateRoot;
import com.dev.batu.authentication_module.common.BaseEntity;
import com.dev.batu.authentication_module.domain.valueobject.LoginAttemptId;
import com.fasterxml.jackson.databind.ser.Serializers;

import java.time.ZonedDateTime;

public class LoginAttempt extends BaseEntity<LoginAttemptId> {

    private final ZonedDateTime attemptedAt;

    private  boolean isSuccess;

    public LoginAttempt(LoginAttemptId loginAttemptId, ZonedDateTime attemptedAt, boolean isSuccess) {
        super.setId(loginAttemptId);
        this.attemptedAt = attemptedAt;
        this.isSuccess = isSuccess;
    }

    public ZonedDateTime getAttemptedAt() {
        return attemptedAt;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}

