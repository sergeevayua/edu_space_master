package ru.edu.space.app.service.access.impl;

import org.springframework.stereotype.Service;
import ru.edu.space.app.entity.course.Course;
import ru.edu.space.app.entity.course.UserPermissions;
import ru.edu.space.app.entity.user.Role;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.repository.course.UserPermissionsDao;
import ru.edu.space.app.service.access.AccessControlService;
import ru.edu.space.app.service.access.AccessCourseControlService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author agerus
 */
@Service
public class AccessCourseControlServiceImpl implements AccessCourseControlService {

    private final AccessControlService accessControlService;

    private final UserPermissionsDao userPermissionsDao;

    public AccessCourseControlServiceImpl(AccessControlService accessControlService, UserPermissionsDao userPermissionsDao) {
        this.accessControlService = accessControlService;
        this.userPermissionsDao = userPermissionsDao;
    }

    @Override
    public boolean isAllowToUpdateCourse(User user) {
        return false;
    }

    @Override
    public boolean isAllowToGetCourse(User user) {
        return false;
    }

    @Override
    public boolean isAllowToDeleteCourse(Long courseId, User user) {
        UserPermissions userPermissions = getDao().getByUserId(user.getId());
        if (userPermissions == null)
            return false;
        return accessControlService.permissionForDelete(user);
    }

    @Override
    public UserPermissions createUserPermission(Course course, User user, List<Role> roleList) {
        UserPermissions entity = new UserPermissions();
        List<String> permissions = roleList.stream().map(Role::getPermission).collect(Collectors.toList());
        entity.setUser(user);
        entity.setCourse(new HashSet<>(Arrays.asList(course)));
        entity.setPermissions(permissions);
        getDao().save(entity);
        return entity;
    }

    @Override
    public List<UserPermissions> getAllById(Long userId) {
        return getDao().getAllByUserId(userId);
    }

//    @Override
//    public List<Long> getAllUserCourseIdsByRole(User user, Role role) {
//        if (!getDao().existsById(user.getId())) {
//            String message = String.format("This user doesn't exist");
//            throw new DeniedException(message);
//        }
//        List<Long> ids = new ArrayList<>();
//
//        UserPermissions userPermissions = getDao().getByUserId(user.getId());
//        Set<Course> courses = userPermissions.getCourse();
//        return null;
//    }

    private UserPermissionsDao getDao() {
        return userPermissionsDao;
    }
}
