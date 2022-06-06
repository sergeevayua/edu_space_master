package ru.edu.space.app.service.course.impl;

import org.springframework.stereotype.Service;
import ru.edu.space.app.entity.course.Chapter;
import ru.edu.space.app.entity.course.Course;
import ru.edu.space.app.entity.course.Exercise;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.exception.DeniedException;
import ru.edu.space.app.logger.ELogger;
import ru.edu.space.app.logger.IELog;
import ru.edu.space.app.repository.course.ExerciseDao;
import ru.edu.space.app.service.AbstractBaseEntityService;
import ru.edu.space.app.service.RouteParamsValidationRuleService;
import ru.edu.space.app.service.course.ChapterService;
import ru.edu.space.app.service.course.ExerciseService;

import java.util.List;

/**
 * @author agerus
 */
@Service
public class ExerciseServiceImpl extends AbstractBaseEntityService<Long, Exercise, ExerciseDao> implements ExerciseService {

    private static final String PREFIX = "[ExerciseService] ";
    protected static IELog log = ELogger.getLogger(ExerciseServiceImpl.class);

    private final ExerciseDao exerciseDao;
    private final RouteParamsValidationRuleService routeParamsValidationRuleService;
    private final ChapterService chapterService;

    public ExerciseServiceImpl(ExerciseDao exerciseDao,
                               RouteParamsValidationRuleService routeParamsValidationRuleService,
                               ChapterService chapterService) {
        this.exerciseDao = exerciseDao;
        this.routeParamsValidationRuleService = routeParamsValidationRuleService;
        this.chapterService = chapterService;
    }

    @Override
    protected ExerciseDao getDao() {
        return exerciseDao;
    }

    @Override
    protected Class<Exercise> getEntityClass() {
        return Exercise.class;
    }

    @Override
    protected void customLogicBeforeCreate(Exercise entity, User user, Object... params) {
        setChapter(entity, user, params);
      //  validateEntity(entity);
    }

    @Override
    protected void customLogicBeforeUpdate(Exercise entity, User user, Object... params) {

    }

    @Override
    protected void customLogicBeforeRemove(Exercise entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterCreate(Exercise entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterUpdate(Exercise entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterRemove(Exercise entity, User user, Object... params) {

    }

    @Override
    protected boolean entityIsAllowedCreate(Exercise entity, User user) {
        return true;
    }

    @Override
    protected boolean entityIsAllowedUpdate(Exercise entity, User user) {
        return true;
    }

    @Override
    protected boolean entityIsAllowedDelete(Exercise entity, User user) {
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

    private void setChapter(Exercise entity, User user, Object[] params) {
        Long chapterId = routeParamsValidationRuleService.getId(params, 0, "chapter id not found in params");
        Chapter chapter = chapterService.getById(chapterId, user);
        entity.setChapter(chapter);
    }

    private void validateEntity(Exercise entity) {
        Exercise exercise = getDao().getByChapterAndNumeration(entity.getChapter().getId(), entity.getNumeration());
        if (exercise != null) {
            throw new DeniedException(String.format("exercise with numeration = '%s' already exist", entity.getNumeration()));
        }
    }

    @Override
    public List<Exercise> getAllByChapterId(Long moduleId, User user) {
        if (!entityIsAllowedGet(user)) {
            String message = String.format("You haven't permission for getAll users");
            throw new DeniedException(message);
        }
        List<Exercise> entities = getDao().getAllExercisesByChapterId(moduleId);
        return entities;
    }
}
