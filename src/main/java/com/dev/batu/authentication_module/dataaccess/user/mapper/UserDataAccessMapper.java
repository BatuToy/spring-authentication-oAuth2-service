package com.dev.batu.authentication_module.dataaccess.user.mapper;

import com.dev.batu.authentication_module.dataaccess.contact.entity.ContactEntity;
import com.dev.batu.authentication_module.dataaccess.user.entity.RoleEntity;
import com.dev.batu.authentication_module.dataaccess.user.entity.UserEntity;
import com.dev.batu.authentication_module.domain.aggregateroot.User;
import com.dev.batu.authentication_module.domain.entity.Contact;
import com.dev.batu.authentication_module.domain.valueobject.ContactId;
import com.dev.batu.authentication_module.domain.entity.Role;
import com.dev.batu.authentication_module.domain.valueobject.RoleId;
import com.dev.batu.authentication_module.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserDataAccessMapper {

    public UserEntity userToUserEntity(User domainUser){
        UserEntity userEntity =  UserEntity.builder()
                .id(domainUser.getId().getValue())
                .email(domainUser.getEmail())
                .userName(domainUser.getUserName())
                .password(domainUser.getEncodedPassword())
                .createdAt(domainUser.getCreatedAt())
                .updatedAt(domainUser.getUpdatedAt())
                .authorities(domainUser.getRoles().stream()
                            .map(this::roleToRoleEntity)
                        .collect(Collectors.toSet()))
                .contact(contactToContactEntity(domainUser.getContact()))
                .build();
        userEntity.getContact().setUser(userEntity);
        userEntity.getAuthorities().forEach(roleEntity -> roleEntity.setUser(userEntity));
        return userEntity;
    }

    public User userEntityToDomainUser(UserEntity userEntity){
        return User.builder()
                .userId(new UserId(userEntity.getId()))
                .email(userEntity.getEmail())
                .userName(userEntity.getUserName())
                .encodedPassword(userEntity.getPassword())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .roles(userEntity.getAuthorities().stream()
                        .map(this::roleEntityToRole)
                        .collect(Collectors.toSet()))
                .contact(contactEntityToContact(userEntity.getContact()))
                .build();
    }

    public ContactEntity contactToContactEntity(Contact contact){
        return ContactEntity.builder()
                .id(contact.getId().getValue())
                .phoneNumber(contact.getCellPhoneNumber())
                .build();
    }

    public Contact contactEntityToContact(ContactEntity contactEntity){
        return new Contact(
                new ContactId(contactEntity.getId()),
                contactEntity.getPhoneNumber()
        );
    }

    private Role roleEntityToRole(RoleEntity roleEntity){
        return new Role(
                new RoleId(roleEntity.getId()),
                new UserId(roleEntity.getUser().getId()),
                roleEntity.getRoleName()
        );
    }

    private RoleEntity roleToRoleEntity(Role role){
        return RoleEntity.builder()
                .id(role.getId().getValue())
                .roleName(role.getRoleName())
                .build();
    }
}
