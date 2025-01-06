package com.dev.batu.authentication_module.dataaccess.adapter;

import com.dev.batu.authentication_module.dataaccess.entity.UserEntity;
import com.dev.batu.authentication_module.ports.output.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    @Override
    public UserEntity save(UserEntity userEntity) {
        return null;
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return null;
    }
}
