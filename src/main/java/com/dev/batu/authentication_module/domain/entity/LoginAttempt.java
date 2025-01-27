package com.dev.batu.authentication_module.domain.entity;

import com.dev.batu.authentication_module.common.AggregateRoot;
import com.dev.batu.authentication_module.common.BaseEntity;
import com.dev.batu.authentication_module.domain.valueobject.LoginAttemptId;
import com.fasterxml.jackson.databind.ser.Serializers;

import java.time.ZonedDateTime;

public class LoginAttempt extends BaseEntity<LoginAttemptId> {

    private final ZonedDateTime attemptedAt;

    private String excMessage;

    private  boolean isSuccess;

    public LoginAttempt(LoginAttemptId loginAttemptId, ZonedDateTime attemptedAt, String excMessage, boolean isSuccess) {
        this.excMessage = excMessage;
        super.setId(loginAttemptId);
        this.attemptedAt = attemptedAt;
        this.isSuccess = isSuccess;
    }

    public LoginAttempt(LoginAttemptId loginAttemptId, ZonedDateTime attemptedAt, boolean isSuccess) {
        super.setId(loginAttemptId);
        this.attemptedAt = attemptedAt;
        this.isSuccess = isSuccess;
    }

    public ZonedDateTime getAttemptedAt() {
        return attemptedAt;
    }

    public String getExcMessage() {
        return excMessage;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public void setExcMessage(String excMessage) {
        this.excMessage = excMessage;
    }
}

