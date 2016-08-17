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

class Test {
    public static void main(String[] args) {
        System.out.println(Role.ADMIN.ordinal());
        System.out.println(Role.MANAGER.ordinal());
        System.out.println(Role.USER.ordinal());
    }
}
