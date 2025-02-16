package com.dev.batu.authentication_module.dataaccess.user.repo;

import com.dev.batu.authentication_module.dataaccess.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    @Query("""
        select U
        from UserEntity as U
        where U.email = :email
        """)
    Optional<UserEntity> findByEmail(@Param("email") String email);

}
