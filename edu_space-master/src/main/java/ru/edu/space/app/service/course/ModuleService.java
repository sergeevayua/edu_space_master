package ru.edu.space.app.service.course;

import ru.edu.space.app.entity.course.Module;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.repository.course.ModuleDao;
import ru.edu.space.app.service.BaseEntityService;

import java.util.List;

/**
 * @author agerus
 */
public interface ModuleService extends BaseEntityService<Long, Module, ModuleDao> {
    List<Module> getAllByCourseGroupIdAndCourseId(Long courseGroupId, Long courseId, User user);
}
