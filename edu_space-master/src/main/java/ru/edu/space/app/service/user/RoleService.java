package ru.edu.space.app.service.user;

import ru.edu.space.app.entity.user.Role;
import ru.edu.space.app.entity.user.User;

import java.util.Collection;
import java.util.List;

/**
 * @author agerus
 */
public interface RoleService {

    public Role getById(Long id);

    public Collection<Role> getAll(User user);

    public List<Role> getRolesByIds(List<Long> ids);
}
