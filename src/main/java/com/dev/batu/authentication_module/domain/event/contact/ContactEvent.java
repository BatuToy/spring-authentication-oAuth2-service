package com.dev.batu.authentication_module.domain.event.contact;

import com.dev.batu.authentication_module.common.DomainEvent;
import com.dev.batu.authentication_module.domain.entity.Contact;

import java.time.ZonedDateTime;

public abstract class ContactEvent implements DomainEvent<Contact> {
    private final Contact contact;
    private final ZonedDateTime createdAt;

    public ContactEvent(Contact contact, ZonedDateTime createdAt) {
        this.contact = contact;
        this.createdAt = createdAt;
    }
}
