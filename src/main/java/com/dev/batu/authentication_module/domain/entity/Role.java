package com.dev.batu.authentication_module.domain.valueobject;

import com.dev.batu.authentication_module.common.BaseEntity;

import java.util.UUID;

public class Role extends BaseEntity<RoleId> {

    private final RoleId roleId;
    private final String roleName;

    private UserId userId;


    public Role(RoleId roleId, UserId userId, String roleName) {
        this.roleId = roleId;
        this.userId = userId;
        this.roleName = roleName;
    }

    public UserId getUserId() {
        return userId;
    }

    public RoleId getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void initializeRole(UserId userId){
        super.setId( new RoleId(UUID.randomUUID()));
        this.userId = userId;
    }
}
