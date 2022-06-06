package ru.edu.space.app.service.course;

import ru.edu.space.app.entity.course.Course;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.repository.course.CourseDao;
import ru.edu.space.app.service.BaseEntityService;

import java.util.List;

/**
 * @author agerus
 */
public interface CourseService extends BaseEntityService<Long, Course, CourseDao> {
    List<Course> getAllByCourseGroupId(Long courseGroupId, User user);

    List<Course> getAllByUserId(User user);
}
