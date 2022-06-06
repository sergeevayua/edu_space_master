package ru.edu.space.app.service.access;


import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.service.user.RoleService;

/**
 * @author agerus
 */
public interface AccessControlService {

    boolean permissionForCreate(User user);
    boolean permissionForUpdate(User user);
    boolean permissionForDelete(User user);

    boolean allowedActionForAdmin(User user);
    boolean allowedActionForUser(User user);

    boolean allowedActionForTeacher(User user);

}
