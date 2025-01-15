package com.dev.batu.authentication_module.ports.output;

import com.dev.batu.authentication_module.domain.aggregateroot.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
}
