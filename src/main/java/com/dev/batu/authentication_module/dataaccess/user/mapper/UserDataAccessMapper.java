package com.dev.batu.authentication_module.dataaccess.user.mapper;

import com.dev.batu.authentication_module.dataaccess.user.entity.UserEntity;
import com.dev.batu.authentication_module.domain.user.Role;
import com.dev.batu.authentication_module.domain.user.User;
import com.dev.batu.authentication_module.domain.user.UserId;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {

    public UserEntity UserToUserEntity(User domainUser){
        return UserEntity.builder()
                .id(domainUser.getId().getValue())
                .email(domainUser.getEmail())
                .userName(domainUser.getUserName())
                .role(domainRoleToRoleEntity(domainUser.getRole()))
                .createdAt(domainUser.getCreatedAt())
                .updatedAt(domainUser.getUpdatedAt())
                .build();
    }

    public User userEntityToDomainUser(UserEntity userEntity){
        return User.builder()
                .userId(new UserId(userEntity.getId()))
                .email(userEntity.getEmail())
                .userName(userEntity.getUsername())
                .password(userEntity.getPassword())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .role(roleEntityToDomainRole(userEntity.getRole()))
                .build();
    }

    private com.dev.batu.authentication_module.dataaccess.user.entity.Role domainRoleToRoleEntity(Role domainRole){
        return com.dev.batu.authentication_module.dataaccess.user.entity.Role.valueOf(domainRole.name());
    }

    private Role roleEntityToDomainRole(com.dev.batu.authentication_module.dataaccess.user.entity.Role roleEntity){
        return Role.valueOf(roleEntity.name());
    }
}
