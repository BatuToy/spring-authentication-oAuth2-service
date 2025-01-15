package com.dev.batu.authentication_module.dataaccess.contact.repo;

import com.dev.batu.authentication_module.dataaccess.contact.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactJpaRepository extends JpaRepository<ContactEntity, UUID> {
}
