package ru.edu.space.app.service;

import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.logger.IELog;

import java.io.Serializable;
import java.util.List;

/**
 * @author agerus
 */
public interface EntityService<PK extends Serializable, T> {

    T getById(PK id, User user);

    List<T> getAll(User user);

    T getByIdWithoutCheckPermission(PK id);

    T create(String json, User user, Object... params);

    T update(PK id, String json, User user, Object... params);

    void remove(PK id, User user, Object... params);

    String getPrefix();

    IELog getLogger();
}
