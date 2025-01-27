package com.dev.batu.authentication_module.domain.valueobject;

import com.dev.batu.authentication_module.common.BaseId;

import java.util.UUID;

public class RoleId extends BaseId<UUID> {
    public RoleId(UUID value) {
        super(value);
    }
}
