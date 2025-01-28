package com.dev.batu.authentication_module.domain.valueobject;

import com.dev.batu.authentication_module.common.valueobject.BaseId;

import java.util.UUID;

public class UserId extends BaseId<UUID> {
    public UserId(UUID value) {
        super(value);
    }
}
