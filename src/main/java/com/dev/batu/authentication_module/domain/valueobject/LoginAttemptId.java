package com.dev.batu.authentication_module.domain.valueobject;

import com.dev.batu.authentication_module.common.BaseId;

import java.util.UUID;

public class LoginAttemptId extends BaseId<UUID> {
    public LoginAttemptId(UUID value) {
        super(value);
    }
}
