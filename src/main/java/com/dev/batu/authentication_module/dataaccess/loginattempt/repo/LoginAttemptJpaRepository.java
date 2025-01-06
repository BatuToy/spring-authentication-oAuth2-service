package com.dev.batu.authentication_module.dataaccess.loginattempt.repo;

import com.dev.batu.authentication_module.dataaccess.loginattempt.entity.LoginAttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoginAttemptJpaRepository extends JpaRepository<LoginAttemptEntity, UUID> {
}
