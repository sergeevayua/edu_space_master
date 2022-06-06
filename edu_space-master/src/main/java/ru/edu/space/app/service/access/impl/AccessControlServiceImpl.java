package ru.edu.space.app.service.access.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.space.app.entity.user.Role;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.entity.user.support.RoleEnum;
import ru.edu.space.app.service.access.AccessControlService;
import ru.edu.space.app.service.user.RoleService;

import java.util.Arrays;
import java.util.List;

/**
 * @author agerus
 */
@Service
public class AccessControlServiceImpl implements AccessControlService {

    private final RoleService roleService;

    @Autowired
    public AccessControlServiceImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public boolean permissionForCreate(User user) {
        Role role = roleService.getById(Long.valueOf(RoleEnum.SUPERADMIN.getRoleId()));
        return user.getRole().contains(role);
    }

    @Override
    public boolean permissionForUpdate(User user) {
        Role role = roleService.getById(Long.valueOf(RoleEnum.SUPERADMIN.getRoleId()));
        return user.getRole().contains(role);
    }

    @Override
    public boolean permissionForDelete(User user) {
        Role role = roleService.getById(Long.valueOf(RoleEnum.SUPERADMIN.getRoleId()));
        return user.getRole().contains(role);
    }

    @Override
    public boolean allowedActionForAdmin(User user) {
        Role role = roleService.getById(Long.valueOf(RoleEnum.SUPERADMIN.getRoleId()));
        return user.getRole().contains(role);
    }

    @Override
    public boolean allowedActionForUser(User user) {
        Role role = roleService.getById(Long.valueOf(RoleEnum.USER.getRoleId()));
        return user.getRole().contains(role);
    }

    @Override
    public boolean allowedActionForTeacher(User user) {
        List<Role> roles = roleService.getRolesByIds(Arrays.asList(Long.valueOf(RoleEnum.TEACHER.getRoleId())));
        return user.getRole().containsAll(roles);
    }
}
