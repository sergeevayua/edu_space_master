package ru.edu.space.app.service.course.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.space.app.entity.course.Course;
import ru.edu.space.app.entity.course.CourseGroup;
import ru.edu.space.app.entity.course.Module;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.exception.DeniedException;
import ru.edu.space.app.logger.ELogger;
import ru.edu.space.app.logger.IELog;
import ru.edu.space.app.repository.course.ModuleDao;
import ru.edu.space.app.service.AbstractBaseEntityService;
import ru.edu.space.app.service.RouteParamsValidationRuleService;
import ru.edu.space.app.service.course.CourseGroupService;
import ru.edu.space.app.service.course.CourseService;
import ru.edu.space.app.service.course.ModuleService;

import java.util.List;

/**
 * @author agerus
 */
@Service
public class ModuleServiceImpl extends AbstractBaseEntityService<Long, Module, ModuleDao> implements ModuleService {

    private static final String PREFIX = "[ModuleService] ";
    protected static IELog log = ELogger.getLogger(ModuleServiceImpl.class);

    private final ModuleDao moduleDao;
    private final RouteParamsValidationRuleService routeParamsValidationRuleService;
    private final CourseGroupService courseGroupService;
    private final CourseService courseService;

    @Autowired
    public ModuleServiceImpl(ModuleDao moduleDao,
                             RouteParamsValidationRuleService routeParamsValidationRuleService,
                             CourseGroupService courseGroupService,
                             CourseService courseService) {
        this.moduleDao = moduleDao;
        this.routeParamsValidationRuleService = routeParamsValidationRuleService;
        this.courseGroupService = courseGroupService;
        this.courseService = courseService;
    }

    @Override
    protected ModuleDao getDao() {
        return moduleDao;
    }

    @Override
    protected Class<Module> getEntityClass() {
        return Module.class;
    }

    @Override
    protected void customLogicBeforeCreate(Module entity, User user, Object... params) {
        setCourse(entity, user, params);
    }

    @Override
    protected void customLogicBeforeUpdate(Module entity, User user, Object... params) {

    }

    @Override
    protected void customLogicBeforeRemove(Module entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterCreate(Module entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterUpdate(Module entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterRemove(Module entity, User user, Object... params) {

    }

    @Override
    protected boolean entityIsAllowedCreate(Module entity, User user) {
        return true;
    }

    @Override
    protected boolean entityIsAllowedUpdate(Module entity, User user) {
        return true;
    }

    @Override
    protected boolean entityIsAllowedDelete(Module entity, User user) {
        return true;
    }

    @Override
    protected boolean entityIsAllowedGet(User user) {
        return true;
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public IELog getLogger() {
        return log;
    }

    private void setCourse(Module entity, User user, Object[] params) {
        Long courseGroupId = routeParamsValidationRuleService.getId(params, 0, "course group id not found in params");
        Long courseId = routeParamsValidationRuleService.getId(params, 1, "course  id not found in params");
        Course course = courseService.getById(courseId, user);
        CourseGroup courseGroup = course.getCourseGroup();
        if (course.getCourseGroup() != null && courseGroup.getId().equals(courseGroupId)) {
            entity.setCourse(course);
        }
    }

    @Override
    public List<Module> getAllByCourseGroupIdAndCourseId(Long courseGroupId, Long courseId, User user) {
        if (!entityIsAllowedGet(user)) {
            String message = String.format("You haven't permission for getAll users");
            throw new DeniedException(message);
        }
        List<Module> entities = getDao().getAllModulesByCourse(courseId);
        return entities;
    }
}
