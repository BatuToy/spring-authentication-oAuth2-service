package com.dev.batu.authentication_module.domain.valueobject;

import com.dev.batu.authentication_module.common.BaseId;

import java.util.UUID;

public class ContactId extends BaseId<UUID> {
    public ContactId(UUID value) {
        super(value);
    }
}
