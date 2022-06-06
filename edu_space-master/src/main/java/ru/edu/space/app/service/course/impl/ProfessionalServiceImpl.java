package ru.edu.space.app.service.course.impl;

import org.springframework.stereotype.Service;
import ru.edu.space.app.entity.course.Professional;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.logger.ELogger;
import ru.edu.space.app.logger.IELog;
import ru.edu.space.app.repository.course.ProfessionalDao;
import ru.edu.space.app.service.AbstractBaseEntityService;
import ru.edu.space.app.service.course.ProfessionalService;

/**
 * @author agerus
 */
@Service
public class ProfessionalServiceImpl extends AbstractBaseEntityService<Long, Professional, ProfessionalDao> implements ProfessionalService {

    private static final String PREFIX = "[ProfessionalService] ";
    protected static IELog log = ELogger.getLogger(ProfessionalServiceImpl.class);

    private final ProfessionalDao professionalDao;

    public ProfessionalServiceImpl(ProfessionalDao professionalDao) {
        this.professionalDao = professionalDao;
    }

    @Override
    protected ProfessionalDao getDao() {
        return professionalDao;
    }

    @Override
    protected Class<Professional> getEntityClass() {
        return Professional.class;
    }

    @Override
    protected void customLogicBeforeCreate(Professional entity, User user, Object... params) {

    }

    @Override
    protected void customLogicBeforeUpdate(Professional entity, User user, Object... params) {

    }

    @Override
    protected void customLogicBeforeRemove(Professional entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterCreate(Professional entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterUpdate(Professional entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterRemove(Professional entity, User user, Object... params) {

    }

    @Override
    protected boolean entityIsAllowedCreate(Professional entity, User user) {
        return false;
    }

    @Override
    protected boolean entityIsAllowedUpdate(Professional entity, User user) {
        return false;
    }

    @Override
    protected boolean entityIsAllowedDelete(Professional entity, User user) {
        return false;
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
}
