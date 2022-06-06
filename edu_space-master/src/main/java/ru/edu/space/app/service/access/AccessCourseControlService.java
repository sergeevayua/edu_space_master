package ru.edu.space.app.service.access;

import ru.edu.space.app.entity.course.Course;
import ru.edu.space.app.entity.course.UserPermissions;
import ru.edu.space.app.entity.user.Role;
import ru.edu.space.app.entity.user.User;

import java.util.List;

/**
 * @author agerus
 */
public interface AccessCourseControlService {

    boolean isAllowToUpdateCourse(User user);
    boolean isAllowToGetCourse(User user);
    boolean isAllowToDeleteCourse(Long courseId, User user);
    UserPermissions createUserPermission(Course course, User user, List<Role> roleList);
    List<UserPermissions> getAllById(Long userId);
}
