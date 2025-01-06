package com.dev.batu.authentication_module.domain.attempt;

import com.dev.batu.authentication_module.common.BaseEntity;

import java.time.ZonedDateTime;
import java.util.UUID;

public class LoginAttempt extends BaseEntity<LoginAttemptId> {
    private final ZonedDateTime attemptedAt;
    private  boolean isSuccess;

    public LoginAttempt(LoginAttemptId loginAttemptId, ZonedDateTime attemptedAt, boolean isSuccess) {
        super.setId(loginAttemptId);
        this.attemptedAt = attemptedAt;
        this.isSuccess = isSuccess;
    }

    private LoginAttempt(Builder builder) {
        super.setId(builder.loginAttemptId);
        attemptedAt = builder.attemptedAt;
        isSuccess = builder.isSuccess;
    }


    public static Builder builder() {
        return new Builder();
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

    public static final class Builder {
        private LoginAttemptId loginAttemptId;
        private ZonedDateTime attemptedAt;
        private boolean isSuccess;

        private Builder() {
        }

        public Builder loginAttemptId(LoginAttemptId val) {
            loginAttemptId = val;
            return this;
        }

        public Builder attemptedAt(ZonedDateTime val) {
            attemptedAt = val;
            return this;
        }

        public Builder isSuccess(boolean val) {
            isSuccess = val;
            return this;
        }

        public LoginAttempt build() {
            return new LoginAttempt(this);
        }
    }
}

