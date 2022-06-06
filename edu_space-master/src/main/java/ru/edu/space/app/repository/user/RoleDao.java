package ru.edu.space.app.repository.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.edu.space.app.entity.user.Role;

import java.util.List;


/**
 * @author agerus
 */
@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

    @Query("SELECT roles FROM Role roles WHERE roles.id IN :ids")
    public List<Role> getRoleByIds(@Param("ids") List<Long> ids);
}
