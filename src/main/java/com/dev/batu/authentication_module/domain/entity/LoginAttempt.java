package com.dev.batu.authentication_module.domain.entity;

import com.dev.batu.authentication_module.common.entity.BaseEntity;
import com.dev.batu.authentication_module.domain.valueobject.LoginAttemptId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.dev.batu.authentication_module.common.constant.AppConstants.*;

public class LoginAttempt extends BaseEntity<LoginAttemptId> {

    private ZonedDateTime attemptedAt;

    private String excMessage;

    private boolean isSuccess;

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

    public LoginAttempt(){}

    public void setFailure(String message){
        this.excMessage = message;
        this.isSuccess = false;
        attemptedAt = ZonedDateTime.now(ZoneId.of(UTC));
    }

    public void defaultAttempt(){
        super.setId(new LoginAttemptId(UUID.randomUUID()));
        this.isSuccess = true;
        this.excMessage = DELIMITER;
        attemptedAt = ZonedDateTime.now(ZoneId.of(UTC));
    }

    public ZonedDateTime getAttemptedAt() {
        return attemptedAt;
    }

    public String getExcMessage() {
        return excMessage;
    }

    public boolean isSuccess() {return isSuccess;}

    public void setExcMessage(String excMessage) {
        this.excMessage = excMessage;
    }

}

