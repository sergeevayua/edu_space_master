package ru.edu.space.app.service.course.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.space.app.entity.course.Chapter;
import ru.edu.space.app.entity.course.CourseGroup;
import ru.edu.space.app.entity.course.Module;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.exception.DeniedException;
import ru.edu.space.app.logger.ELogger;
import ru.edu.space.app.logger.IELog;
import ru.edu.space.app.repository.course.ChapterDao;
import ru.edu.space.app.service.AbstractBaseEntityService;
import ru.edu.space.app.service.RouteParamsValidationRuleService;
import ru.edu.space.app.service.course.ChapterService;
import ru.edu.space.app.service.course.ModuleService;

import java.util.List;

/**
 * @author agerus
 */
@Service
public class ChapterServiceImpl extends AbstractBaseEntityService<Long, Chapter, ChapterDao> implements ChapterService {

    private static final String PREFIX = "[ChapterService] ";
    protected static IELog log = ELogger.getLogger(ChapterServiceImpl.class);

    private final ChapterDao chapterDao;
    private final ModuleService moduleService;
    private final RouteParamsValidationRuleService routeParamsValidationRuleService;


    @Autowired
    public ChapterServiceImpl(ChapterDao chapterDao, ModuleService moduleService,
                              RouteParamsValidationRuleService routeParamsValidationRuleService) {
        this.chapterDao = chapterDao;
        this.moduleService = moduleService;
        this.routeParamsValidationRuleService = routeParamsValidationRuleService;
    }

    @Override
    protected ChapterDao getDao() {
        return chapterDao;
    }

    @Override
    protected Class<Chapter> getEntityClass() {
        return Chapter.class;
    }

    @Override
    protected void customLogicBeforeCreate(Chapter entity, User user, Object... params) {
        setModule(entity, user, params);
    }

    @Override
    protected void customLogicBeforeUpdate(Chapter entity, User user, Object... params) {

    }

    @Override
    protected void customLogicBeforeRemove(Chapter entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterCreate(Chapter entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterUpdate(Chapter entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterRemove(Chapter entity, User user, Object... params) {

    }

    @Override
    protected boolean entityIsAllowedCreate(Chapter entity, User user) {
        return true;
    }

    @Override
    protected boolean entityIsAllowedUpdate(Chapter entity, User user) {
        return true;
    }

    @Override
    protected boolean entityIsAllowedDelete(Chapter entity, User user) {
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

    private void setModule(Chapter entity, User user, Object[] params) {
        Long moduleId = routeParamsValidationRuleService.getId(params, 0, "module id not found in params");
        Module module = moduleService.getById(moduleId, user);
        entity.setModule(module);
    }

    @Override
    public List<Chapter> getAllByModuleId(Long moduleId, User user) {
        if (!entityIsAllowedGet(user)) {
            String message = String.format("You haven't permission for getAll users");
            throw new DeniedException(message);
        }
        List<Chapter> entities = getDao().getAllChaptersByModuleId(moduleId);
        return entities;
    }
}
