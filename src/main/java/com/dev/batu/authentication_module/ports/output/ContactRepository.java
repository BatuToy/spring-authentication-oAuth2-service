package com.dev.batu.authentication_module.ports.output;

import com.dev.batu.authentication_module.domain.entity.Contact;

public interface ContactRepository {
    Contact save(Contact contact);
}
