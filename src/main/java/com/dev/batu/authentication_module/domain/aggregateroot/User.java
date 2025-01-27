package com.dev.batu.authentication_module.domain.aggregateroot;

import com.dev.batu.authentication_module.common.BaseEntity;
import com.dev.batu.authentication_module.domain.entity.Contact;
import com.dev.batu.authentication_module.domain.exception.UserDomainException;
import com.dev.batu.authentication_module.domain.entity.Role;
import com.dev.batu.authentication_module.domain.valueobject.UserId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class User extends BaseEntity<UserId> implements UserDetails {

    public static final Logger log = Logger.getLogger(User.class.getName());

    private final String email;
    private final String encodedPassword;
    private final Contact contact;
    private final ZonedDateTime createdAt;

    private String userName;
    private ZonedDateTime updatedAt;
    private Set<Role> roles = new HashSet<>();

    public User(String userName, String email, String encodedPassword, Contact contact, ZonedDateTime createdAt, ZonedDateTime updatedAt, Set<Role> roles) {
        this.userName = userName;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.contact = contact;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.roles = roles;
    }

    public void initializeUser(){
        super.setId(new UserId( UUID.randomUUID()));
        initializeContactToUser();
        initializeRoleToUser();
    }

    public void validateUser(){
        validateId();
        validatePasswordAndEmail();
        validateRoles();
    }

    private void initializeContactToUser(){
        contact.initializeContact(super.getId());
    }

    private void initializeRoleToUser(){
        HashSet<Role> roles = new HashSet<>();
        Role role = new Role();
        Role new_role = role.initializeRole(super.getId());
        roles.add(new_role);
        this.roles = roles;
    }

    private void validateRoles(){
        if(this.roles != null){
            log.info("Roles are not in the correct statement!");
            throw new UserDomainException("Roles are not in the correct statement!");
        }
    }


    private void validateId() {
        if(super.getId() != null){
            log.info("User is not in correct state for initialization!");
            throw new UserDomainException("User is not in correct state for initialization!");
        }
    }

    private void validatePasswordAndEmail() {
        if(this.encodedPassword.isEmpty() || this.email.isEmpty()){
            log.info("Password or email can not be null!");
            throw new UserDomainException("Password or email can not be a null value!");
        }
    }

    private User(Builder builder) {
        super.setId(builder.userId);
        userName = builder.userName;
        email = builder.email;
        encodedPassword = builder.encodedPassword;
        contact = builder.contact;
        createdAt = builder.createdAt;
        updatedAt = builder.updatedAt;
        roles = builder.roles;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getEmail() {
        return this.email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getUserName() {
        return userName;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Contact getContact() {
        return contact;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.encodedPassword;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public static final class Builder {
        private UserId userId;
        private String userName;
        private String email;
        private String encodedPassword;
        private Contact contact;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
        private Set<Role> roles;

        private Builder() {
        }

        public Builder userId(UserId val) {
            userId = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder encodedPassword(String val) {
            encodedPassword = val;
            return this;
        }

        public Builder contact(Contact val) {
            contact = val;
            return this;
        }

        public Builder roles(Set<Role> val){
            roles = val;
            return this;
        }

        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder updatedAt(ZonedDateTime val) {
            updatedAt = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
