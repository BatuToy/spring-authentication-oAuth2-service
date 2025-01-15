package com.dev.batu.authentication_module.dataaccess.contact.adapter;

import com.dev.batu.authentication_module.dataaccess.contact.mapper.ContactDataAccessMapper;
import com.dev.batu.authentication_module.dataaccess.contact.repo.ContactJpaRepository;
import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.domain.entity.Contact;
import com.dev.batu.authentication_module.ports.output.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ContactRepositoryImpl implements ContactRepository {

    private final ContactJpaRepository contactJpaRepository;
    private final ContactDataAccessMapper contactDataAccessMapper;

    @Override
    public Contact save(Contact contact, User user) {
        return contactDataAccessMapper
                .contactEntityToContact(
                        contactJpaRepository
                                .save(contactDataAccessMapper.contactToContactEntity(user, contact)));
    }
}
