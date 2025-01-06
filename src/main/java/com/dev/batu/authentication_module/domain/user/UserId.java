package com.dev.batu.authentication_module.domain.user;

import com.dev.batu.authentication_module.common.BaseId;

import java.util.UUID;

public class UserId extends BaseId<UUID> {
    public UserId(UUID value) {
        super(value);
    }
}
