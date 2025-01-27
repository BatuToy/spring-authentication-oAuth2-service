package com.dev.batu.authentication_module.dataaccess.contact.adapter;

import com.dev.batu.authentication_module.dataaccess.contact.repo.ContactJpaRepository;
import com.dev.batu.authentication_module.dataaccess.user.mapper.UserDataAccessMapper;
import com.dev.batu.authentication_module.domain.entity.Contact;
import com.dev.batu.authentication_module.ports.output.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ContactRepositoryImpl implements ContactRepository {

    private final ContactJpaRepository contactJpaRepository;
    private final UserDataAccessMapper userDataAccessMapper;

    @Override
    public Contact save(Contact contact) {
        return userDataAccessMapper
                .contactEntityToContact(
                        contactJpaRepository
                                .save(userDataAccessMapper.contactToContactEntity(contact)));
    }
}
