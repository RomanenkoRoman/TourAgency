package ua.nure.romanenko.SummaryTask4.db;

import ua.nure.romanenko.SummaryTask4.db.entity.User;

/**
 * Role entity.
 */

public enum Role {
    ADMIN, MANAGER, USER;

    public static Role getRole(User user) {
        String role = user.getRole();
        return Role.valueOf(role.toUpperCase());
    }

    public String getName() {
        return name().toLowerCase();
    }

}

