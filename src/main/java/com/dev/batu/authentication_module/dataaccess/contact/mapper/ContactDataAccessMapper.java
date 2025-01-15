package com.dev.batu.authentication_module.dataaccess.contact.mapper;

import com.dev.batu.authentication_module.dataaccess.contact.entity.ContactEntity;
import com.dev.batu.authentication_module.dataaccess.user.mapper.UserDataAccessMapper;
import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.domain.entity.Contact;
import com.dev.batu.authentication_module.domain.valueobject.ContactId;
import com.dev.batu.authentication_module.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ContactDataAccessMapper {

    private final UserDataAccessMapper userDataAccessMapper;

    public ContactEntity contactToContactEntity(User user, Contact contact){
        return ContactEntity.builder()
                .id(contact.getId().getValue())
                .user(userDataAccessMapper.userToUserEntity(user))
                .phoneNumber(contact.getCellPhoneNumber())
                .build();
    }

    public Contact contactEntityToContact(ContactEntity contactEntity){
        return new Contact(
                new ContactId(contactEntity.getId()),
                contactEntity.getPhoneNumber(),
                new UserId(contactEntity.getUser().getId())
        );
    }
}
