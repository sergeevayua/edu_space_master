package ru.edu.space.app.service.user.impl;


import org.springframework.stereotype.Service;
import ru.edu.space.app.entity.user.Role;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.exception.NotFoundException;
import ru.edu.space.app.repository.user.RoleDao;
import ru.edu.space.app.service.user.RoleService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author agerus
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getById(Long id) {
        Optional<Role> role = getDao().findById(id);

        if (!role.isPresent()) {
            String msg = String.format("cannot get role by id = '%s'!", id);
            throw new NotFoundException(msg);
        }
        return role.get();
    }

    @Override
    public List<Role> getRolesByIds(List<Long> ids) {
        List<Role> roles = getDao().getRoleByIds(ids);
        if (roles == null) {
            String msg = String.format("cannot get roles");
            throw new NotFoundException(msg);
        }
        return roles;
    }

    @Override
    public Collection<Role> getAll(User user) {
        List<Role> roles = getDao().findAll();
        if (roles == null) {
            String msg = String.format("cannot getAll roles!");
            throw new NotFoundException(msg);
        }

        return roles;
    }

    private RoleDao getDao() {
        return roleDao;
    }
}
