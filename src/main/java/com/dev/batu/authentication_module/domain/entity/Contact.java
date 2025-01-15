package com.dev.batu.authentication_module.domain.entity;

import com.dev.batu.authentication_module.common.BaseEntity;
import com.dev.batu.authentication_module.domain.valueobject.ContactId;
import com.dev.batu.authentication_module.domain.valueobject.UserId;

import java.util.UUID;

public class Contact extends BaseEntity<ContactId> {
    private UserId userId;
    private final String cellPhoneNumber;

    public Contact(ContactId contactId, String cellPhoneNumber, UserId userId) {
        super.setId(contactId);
        this.cellPhoneNumber = cellPhoneNumber;
        this.userId = userId;
    }

    public Contact(String cellPhoneNumber){
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public void initializeContact(UserId userId){
        super.setId( new ContactId(UUID.randomUUID()));
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }
}
