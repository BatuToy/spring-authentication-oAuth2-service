package com.dev.batu.authentication_module.domain.user;

import com.dev.batu.authentication_module.common.BaseEntity;

import java.time.ZonedDateTime;

public class User extends BaseEntity<UserId>  {
    private final String userName;
    private final String email;
    private final String password;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;
    private Role role;

    public User(UserId userId, String userName, String email, String password, ZonedDateTime createdAt, ZonedDateTime updatedAt, Role role) {
        super.setId(userId);
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = role;
    }

    private User(Builder builder) {
        super.setId(builder.userId);
        userName = builder.userName;
        email = builder.email;
        password = builder.password;
        createdAt = builder.createdAt;
        updatedAt = builder.updatedAt;
        role = builder.role;
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

    public String getPassword() {
        return this.password;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Role getRole(){
        return this.role;
    }


    public static final class Builder {
        private UserId userId;
        private String userName;
        private String email;
        private String password;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
        private Role role;

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

        public Builder password(String val) {
            password = val;
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

        public Builder role(Role val){
            role = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
