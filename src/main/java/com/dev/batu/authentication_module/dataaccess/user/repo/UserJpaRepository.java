package com.dev.batu.authentication_module.dataaccess.repo;

import com.dev.batu.authentication_module.dataaccess.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    @Query("""
        select U
        from UserEntity as U
        where U.email = :email
        """)
    Optional<UserEntity> findByEmail(@Param("email") String email);
}
