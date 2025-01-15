package com.dev.batu.authentication_module.domain.event.contact;

import com.dev.batu.authentication_module.domain.entity.Contact;

import java.time.ZonedDateTime;

public class ContactCreatedEvent extends ContactEvent{
    public ContactCreatedEvent(Contact contact, ZonedDateTime createdAt) {
        super(contact, createdAt);
    }
}
