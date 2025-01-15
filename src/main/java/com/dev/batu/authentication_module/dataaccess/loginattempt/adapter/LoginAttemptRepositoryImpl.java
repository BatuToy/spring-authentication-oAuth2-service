package com.dev.batu.authentication_module.dataaccess.loginattempt.adapter;

import com.dev.batu.authentication_module.dataaccess.loginattempt.mapper.LoginAttemptDataAccessMapper;
import com.dev.batu.authentication_module.dataaccess.loginattempt.repo.LoginAttemptJpaRepository;
import com.dev.batu.authentication_module.domain.entity.LoginAttempt;
import com.dev.batu.authentication_module.ports.output.LoginAttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LoginAttemptRepositoryImpl implements LoginAttemptRepository {

    private final LoginAttemptDataAccessMapper loginAttemptDataAccessMapper;
    private final LoginAttemptJpaRepository loginAttemptJpaRepository;

    @Override
    public LoginAttempt save(LoginAttempt loginAttempt) {
        return loginAttemptDataAccessMapper
                .loginAttemptEntityToLoginAttempt
                        (loginAttemptJpaRepository.save
                                (loginAttemptDataAccessMapper
                                        .loginAttemptToLoginAttemptEntity(loginAttempt)));
    }

    @Override
    public List<LoginAttempt> listAllAttempts() {
        return loginAttemptJpaRepository.findAll()
                .stream()
                    .map(loginAttemptDataAccessMapper::loginAttemptEntityToLoginAttempt)
                        .toList();
    }
}
