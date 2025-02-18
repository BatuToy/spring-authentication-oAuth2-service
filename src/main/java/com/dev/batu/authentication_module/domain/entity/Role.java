package com.dev.batu.authentication_module.domain.entity;

import com.dev.batu.authentication_module.common.entity.BaseEntity;
import com.dev.batu.authentication_module.domain.valueobject.RoleId;
import com.dev.batu.authentication_module.domain.valueobject.UserId;

import java.util.UUID;

public class Role extends BaseEntity<RoleId> {

    private String roleName;
    private UserId userId;

    public Role() {
    }

    public Role(RoleId roleId, UserId userId, String roleName) {
        super.setId(roleId);
        this.userId = userId;
        this.roleName = roleName;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public Role initializeRoleUser(UserId userId){
        super.setId( new RoleId(UUID.randomUUID()));
        this.roleName = "ROLE_USER";
        this.userId = userId;
        return this;
    }

    public Role initializeRoleAdmin(UserId userId){
        super.setId(new RoleId(UUID.randomUUID()));
        this.roleName = "ROLE_ADMIN";
        this.userId = userId;
        return this;
    }
}
