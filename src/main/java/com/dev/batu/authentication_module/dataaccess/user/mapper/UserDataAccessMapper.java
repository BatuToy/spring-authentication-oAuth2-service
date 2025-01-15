package com.dev.batu.authentication_module.dataaccess.user.mapper;

import com.dev.batu.authentication_module.dataaccess.user.entity.UserEntity;
import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDataAccessMapper {

    private final PasswordEncoder passwordEncoder;

    public UserEntity userToUserEntity(User domainUser){
        return UserEntity.builder()
                .id(domainUser.getId().getValue())
                .email(domainUser.getEmail())
                .userName(domainUser.getUserName())
                .password(passwordEncoder.encode(domainUser.getEncodedPassword()))
                .createdAt(domainUser.getCreatedAt())
                .updatedAt(domainUser.getUpdatedAt())
                .authorities(domainUser.getAuthorities())
                .build();
    }

    public User userEntityToDomainUser(UserEntity userEntity){
        return User.builder()
                .userId(new UserId(userEntity.getId()))
                .email(userEntity.getEmail())
                .userName(userEntity.getUserName())
                .encodedPassword(userEntity.getPassword())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .authorities(userEntity.getAuthorities())
                .build();
    }

}
