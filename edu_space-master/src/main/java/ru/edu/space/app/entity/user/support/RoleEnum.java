package ru.edu.space.app.entity.user.support;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author agerus
 */
public enum RoleEnum {

    SUPERADMIN(1),
    TEACHER(2),
    USER(3);

    private final int roleId;
    private static final Set<Integer> roleIds;

    static {
        roleIds = Stream.of(SUPERADMIN.roleId, TEACHER.roleId, USER.roleId).collect(Collectors.toSet());
    }

    RoleEnum(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public static boolean isValid(int roleId) {
        return roleIds.contains(roleId);
    }
}
