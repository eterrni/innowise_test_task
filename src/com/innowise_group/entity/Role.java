package com.innowise_group.entity;

import java.io.Serializable;

public enum Role implements Serializable {
    USER(1), CUSTOMER(1),
    ADMIN(2), PROVIDER(2),
    SUPER_ADMIN(3);

    private final int roleLevel;

    Role(int roleLevel) {
        this.roleLevel = roleLevel;
    }

    public int getRoleLevel() {
        return roleLevel;
    }
}
