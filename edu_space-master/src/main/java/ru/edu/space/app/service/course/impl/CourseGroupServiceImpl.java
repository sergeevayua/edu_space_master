package ru.edu.space.app.service.course.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.space.app.entity.course.CourseGroup;
import ru.edu.space.app.entity.course.Professional;
import ru.edu.space.app.entity.course.support.ChartDto;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.exception.DeniedException;
import ru.edu.space.app.logger.ELogger;
import ru.edu.space.app.logger.IELog;
import ru.edu.space.app.repository.course.CourseGroupDao;
import ru.edu.space.app.service.AbstractBaseEntityService;
import ru.edu.space.app.service.RouteParamsValidationRuleService;
import ru.edu.space.app.service.access.AccessControlService;
import ru.edu.space.app.service.course.CourseGroupService;
import ru.edu.space.app.service.course.ProfessionalService;

import java.math.BigInteger;
import java.util.*;

/**
 * @author agerus
 */
@Service
public class CourseGroupServiceImpl extends AbstractBaseEntityService<Long, CourseGroup, CourseGroupDao> implements CourseGroupService {

    private static final String PREFIX = "[CourseGroupService] ";
    protected static IELog log = ELogger.getLogger(CourseGroupServiceImpl.class);

    private final CourseGroupDao courseGroupDao;
    private final AccessControlService accessControlService;
    private final RouteParamsValidationRuleService routeParamsValidationRuleService;
    private final ProfessionalService professionalService;


    @Autowired
    public CourseGroupServiceImpl(CourseGroupDao courseGroupDao,
                                  AccessControlService accessControlService,
                                  RouteParamsValidationRuleService routeParamsValidationRuleService,
                                  ProfessionalService professionalService) {
        this.courseGroupDao = courseGroupDao;
        this.accessControlService = accessControlService;
        this.routeParamsValidationRuleService = routeParamsValidationRuleService;
        this.professionalService = professionalService;
    }

    @Override
    protected CourseGroupDao getDao() {
        return courseGroupDao;
    }

    @Override
    protected Class<CourseGroup> getEntityClass() {
        return CourseGroup.class;
    }

    @Override
    protected void customLogicBeforeCreate(CourseGroup entity, User user, Object... params) {
        setProfessional(entity, user, params);
    }

    @Override
    protected void customLogicBeforeUpdate(CourseGroup entity, User user, Object... params) {

    }

    @Override
    protected void customLogicBeforeRemove(CourseGroup entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterCreate(CourseGroup entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterUpdate(CourseGroup entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterRemove(CourseGroup entity, User user, Object... params) {

    }

    @Override
    protected boolean entityIsAllowedCreate(CourseGroup entity, User user) {
        return accessControlService.allowedActionForAdmin(user);
    }

    @Override
    protected boolean entityIsAllowedUpdate(CourseGroup entity, User user) {
        return accessControlService.allowedActionForTeacher(user);
    }

    @Override
    protected boolean entityIsAllowedDelete(CourseGroup entity, User user) {
        return accessControlService.allowedActionForAdmin(user);
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

    private void setProfessional(CourseGroup entity, User user, Object[] params) {
        Long professionalId = routeParamsValidationRuleService.getId(params, 0, "professional id not found in params");
        Professional professional = professionalService.getById(professionalId, user);
        entity.setProfessional(professional);
    }

    @Override
    public List<CourseGroup> getAllByProfessionalId(Long profId, User user) {
        if (!entityIsAllowedGet(user)) {
            String message = String.format("You haven't permission for getAll users");
            throw new DeniedException(message);
        }
        List<CourseGroup> entities = getDao().getAllCourseGroupsByProfessionalId(profId);
        return entities;
    }

    @Override
    public ChartDto getAggregatedChartDataByCourse(User user) {
        ChartDto chartDto = new ChartDto();
        List<List<String>> chartList = new ArrayList<>();
        List<Object[]> list = getDao().getAggregatedChartData();
        for (Object[] arr : list) {
            chartList.add(Arrays.asList((String)arr[0], arr[1].toString()));
        }
        chartDto.setChartData(chartList);
        return chartDto;
    }
}
