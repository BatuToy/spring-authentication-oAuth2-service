package com.dev.batu.authentication_module.domain.aggregateroot;

import com.dev.batu.authentication_module.common.BaseEntity;
import com.dev.batu.authentication_module.domain.entity.Contact;
import com.dev.batu.authentication_module.domain.exception.UserDomainException;
import com.dev.batu.authentication_module.domain.valueobject.UserId;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class User extends BaseEntity<UserId>  {

    public static final Logger log = Logger.getLogger(User.class.getName());

    private final String userName;
    private final String email;
    private final String encodedPassword;
    private final Contact contact;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;
    private List<String> authorities;

    public User(UserId userId, String userName, String email, String encodedPassword, Contact contact, ZonedDateTime createdAt, ZonedDateTime updatedAt, List<String> authorities) {
        super.setId(userId);
        this.userName = userName;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.contact = contact;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.authorities = authorities;
    }

    public void initializeUser(){
        super.setId( new UserId( UUID.randomUUID()));
        this.contact.initializeContact(super.getId());
        this.authorities = List.of("ROLE_USER");
    }

    public void validateUser(){
        validateIdAndAuthorities();
        validatePasswordAndEmail();
    }


    private void validateIdAndAuthorities() {
        if(super.getId().getValue() != null || !this.authorities.isEmpty()){
            log.info("User is not in correct state for initialization!");
            throw new UserDomainException("User is not in correct state for initialization!");
        }
    }

    private void validatePasswordAndEmail() {
        if(!this.encodedPassword.isEmpty() || !this.email.isEmpty()){
            log.info("Password or email already initialized!");
            throw new UserDomainException("Password or email already initialized!");
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
        authorities = builder.authorities;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getEmail() {
        return this.email;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getEncodedPassword() {
        return this.encodedPassword;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<String> getAuthorities() {return authorities;}

    public Contact getContact() {return contact;}

    public static final class Builder {
        private UserId userId;
        private String userName;
        private String email;
        private String encodedPassword;
        private Contact contact;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
        private List<String> authorities;

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

        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder updatedAt(ZonedDateTime val) {
            updatedAt = val;
            return this;
        }

        public Builder authorities(List<String> val){
            authorities = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
