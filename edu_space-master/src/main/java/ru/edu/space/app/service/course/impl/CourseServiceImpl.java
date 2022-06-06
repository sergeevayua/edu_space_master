package ru.edu.space.app.service.course.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.space.app.entity.course.Course;
import ru.edu.space.app.entity.course.CourseGroup;
import ru.edu.space.app.entity.course.UserPermissions;
import ru.edu.space.app.entity.user.Role;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.exception.DeniedException;
import ru.edu.space.app.logger.ELogger;
import ru.edu.space.app.logger.IELog;
import ru.edu.space.app.repository.course.CourseDao;
import ru.edu.space.app.service.AbstractBaseEntityService;
import ru.edu.space.app.service.RouteParamsValidationRuleService;
import ru.edu.space.app.service.access.AccessControlService;
import ru.edu.space.app.service.access.AccessCourseControlService;
import ru.edu.space.app.service.course.CourseGroupService;
import ru.edu.space.app.service.course.CourseService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author agerus
 */
@Service
public class CourseServiceImpl extends AbstractBaseEntityService<Long, Course, CourseDao>
        implements CourseService {

    private static final String PREFIX = "[CourseService] ";
    protected static IELog log = ELogger.getLogger(CourseServiceImpl.class);

    private final CourseDao courseDao;
    private final AccessControlService accessControlService;
    private final AccessCourseControlService accessCourseControlService;
    private final RouteParamsValidationRuleService routeParamsValidationRuleService;
    private final CourseGroupService courseGroupService;


    @Autowired
    public CourseServiceImpl(CourseDao courseDao,
                             AccessControlService accessControlService,
                             AccessCourseControlService accessCourseControlService, RouteParamsValidationRuleService routeParamsValidationRuleService,
                             CourseGroupService courseGroupService) {
        this.courseDao = courseDao;
        this.accessControlService = accessControlService;
        this.accessCourseControlService = accessCourseControlService;
        this.routeParamsValidationRuleService = routeParamsValidationRuleService;
        this.courseGroupService = courseGroupService;
    }

    @Override
    protected CourseDao getDao() {
        return courseDao;
    }

    @Override
    protected Class<Course> getEntityClass() {
        return Course.class;
    }

    @Override
    public Course create(String json, User user, Object... params) {
        Course entity = getEntityFromJson(json);
        if (!entityIsAllowedCreate(entity, user)) {
            String message = String.format("You haven't permission for create course");
            throw new DeniedException(message);
        }
        customLogicBeforeCreate(entity, user, params);
        getDao().saveAndFlush(entity);
        customLogicAfterCreate(entity, user, params);
        return entity;
    }

    @Override
    protected void customLogicBeforeCreate(Course entity, User user, Object... params) {
        setCourseGroup(entity, user, params);
    }

    @Override
    protected void customLogicBeforeUpdate(Course entity, User user, Object... params) {

    }

    @Override
    protected void customLogicBeforeRemove(Course entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterCreate(Course entity, User user, Object... params) {
        setUserAndPermissions(entity, user, params);
    }

    @Override
    protected void customLogicAfterUpdate(Course entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterRemove(Course entity, User user, Object... params) {

    }

    @Override
    protected boolean entityIsAllowedCreate(Course entity, User user) {
        return accessControlService.allowedActionForTeacher(user);
    }

    @Override
    protected boolean entityIsAllowedUpdate(Course entity, User user) {
        return true;
    }

    @Override
    protected boolean entityIsAllowedDelete(Course entity, User user) {
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

    private void setCourseGroup(Course entity, User user, Object[] params) {
        Long courseGroupId = routeParamsValidationRuleService.getId(params, 0, "course group id not found in params");
        CourseGroup courseGroup = courseGroupService.getById(courseGroupId, user);
        entity.setCourseGroup(courseGroup);
    }

    private void setUserAndPermissions(Course entity, User user, Object[] params) {
        List<Role> roleList = user.getRole();
        accessCourseControlService.createUserPermission(entity, user, roleList);
    }

    @Override
    public List<Course> getAllByCourseGroupId(Long courseGroupId, User user) {
        if (!entityIsAllowedGet(user)) {
            String message = String.format("You haven't permission for getAll users");
            throw new DeniedException(message);
        }
        List<Course> entities = getDao().getAllCoursesByCourseGroupId(courseGroupId);
        return entities;
    }

    @Override
    public List<Course> getAllByUserId(User user) {
        if (!entityIsAllowedGet(user)) {
            String message = String.format("You haven't permission for getAll users");
            throw new DeniedException(message);
        }
        List<UserPermissions> userPermissions = accessCourseControlService
                .getAllById(user.getId());
        return userPermissions
                .stream()
                .map(permission -> permission.getCourse().toArray(new Course[0])[0])
                .collect(Collectors.toList());
    }

    //    @Override
//    public List<Course> getAll(User user) {
//        return new ArrayList<>(getDao().getAllUserCoursesByPermission(user.getId()));
//    }
}
