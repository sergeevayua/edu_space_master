package ru.edu.space.app.service;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.space.app.entity.BaseEntity;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.exception.DeniedException;
import ru.edu.space.app.exception.NotFoundException;
import ru.edu.space.app.util.JacksonUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author agerus
 */
public abstract class AbstractBaseEntityService<PK extends Serializable, T extends BaseEntity, D extends JpaRepository>
        implements BaseEntityService<PK, T, D> {

    protected abstract D getDao();

    protected abstract Class<T> getEntityClass();

    protected abstract void customLogicBeforeCreate(T entity, User user, Object... params);

    protected abstract void customLogicBeforeUpdate(T entity, User user, Object... params);

    protected abstract void customLogicBeforeRemove(T entity, User user, Object... params);


    protected abstract void customLogicAfterCreate(T entity, User user, Object... params);

    protected abstract void customLogicAfterUpdate(T entity, User user, Object... params);

    protected abstract void customLogicAfterRemove(T entity, User user, Object... params);

    protected abstract boolean entityIsAllowedCreate(T entity, User user);

    protected abstract boolean entityIsAllowedUpdate(T entity, User user);

    protected abstract boolean entityIsAllowedDelete(T entity, User user);

    protected abstract boolean entityIsAllowedGet(User user);


    @Override
    public T getById(PK id, User user) {
        if (!entityIsAllowedGet(user)) {
            String message = String.format("You haven't permission for getById user with id = '%s' and login = '%s'", id);
            throw new DeniedException(message);
        }
        Optional<T> entity = (Optional<T>) getDao().findById(id);
        isPresent(id, !entity.isPresent(), "Not found entity with id = '%s'");
        return entity.get();
    }

    @Override
    public List<T> getAll(User user) {
        if (!entityIsAllowedGet(user)) {
            String message = String.format("You haven't permission for getAll users");
            throw new DeniedException(message);
        }
        List<T> entities = getDao().findAll();
        return entities;
    }

    @Override
    public T getByIdWithoutCheckPermission(PK id) {
        Optional<T> entity = getDao().findById(id);
        isPresent(id, !entity.isPresent(), "Not found entity with id = '%s'");
        return entity.get();
    }

    private void isPresent(PK id, boolean b, String s) {
        if (b) {
            String message = String.format(s, id);
            throw new NotFoundException(message);
        }
    }

    @Override
    public T create(String json, User user, Object... params) {
        T entity = getEntityFromJson(json);
        if (!entityIsAllowedCreate(entity, user)) {
            String message = String.format("You haven't permission for create user");
            throw new DeniedException(message);
        }
        customLogicBeforeCreate(entity, user, params);
        getDao().saveAndFlush(entity);
        customLogicAfterCreate(entity, user, params);
        return entity;
    }

    @Override
    public T update(PK id, String json, User user, Object... params) {
        T entity = getById(id, user);
        isPresent(id, entity == null, "Not found user with id = '%s'");
        if (!entityIsAllowedUpdate(entity, user)) {
            String message = String.format("You haven't permission for update user with id = '%s'", id);
            throw new DeniedException(message);
        }
        JacksonUtil.updateFromString(json, entity);
        customLogicBeforeUpdate(entity, user, params);
        getDao().saveAndFlush(entity);
        customLogicAfterUpdate(entity, user, params);
        return entity;
    }

    @Override
    public void remove(PK id, User user, Object... params) {
        T entity = getById(id, user);
        isPresent(id, entity == null, "Not found user with id = '%s'");
        if (!entityIsAllowedDelete(entity, user)) {
            String message = String.format("You haven't permission for delete user with id = '%s'", id);
            throw new DeniedException(message);
        }
        customLogicBeforeRemove(entity, user, params);
        getDao().deleteById(id);
        customLogicAfterRemove(entity, user, params);
    }

    protected T getEntityFromJson(String json) {
        return JacksonUtil.fromString(json, getEntityClass());
    }
}
