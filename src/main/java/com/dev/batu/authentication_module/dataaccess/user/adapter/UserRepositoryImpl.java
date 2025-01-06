package com.dev.batu.authentication_module.dataaccess.user.adapter;

import com.dev.batu.authentication_module.dataaccess.user.mapper.UserDataAccessMapper;
import com.dev.batu.authentication_module.dataaccess.user.repo.UserJpaRepository;
import com.dev.batu.authentication_module.domain.user.User;
import com.dev.batu.authentication_module.ports.output.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserDataAccessMapper userDataAccessMapper;

    @Override
    public User save(User user) {
        return userDataAccessMapper.userEntityToDomainUser(userJpaRepository.save(userDataAccessMapper.UserToUserEntity(user)));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository
                .findByEmail(email)
                .   map(userDataAccessMapper::userEntityToDomainUser);
    }
}
