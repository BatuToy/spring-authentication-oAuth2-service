package com.dev.batu.authentication_module.repo;

import com.dev.batu.authentication_module.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("""
        select U
        from User as U
        where U.email = :email
        """)
    Optional<User> findByEmail(@Param("email") String email);
}
