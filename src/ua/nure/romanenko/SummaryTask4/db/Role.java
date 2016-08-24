package ua.nure.romanenko.SummaryTask4.db;

import ua.nure.romanenko.SummaryTask4.db.entity.User;

/**
 * Role entity.
 */

public enum Role {
    ADMIN, MANAGER, USER;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }

}

