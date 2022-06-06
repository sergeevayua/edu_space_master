package ru.edu.space.app.repository.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.edu.space.app.entity.course.UserPermissions;

import java.util.List;

/**
 * @author agerus
 */
@Repository
public interface UserPermissionsDao extends JpaRepository<UserPermissions, Long> {

    @Query("SELECT up FROM UserPermissions up left join up.user u where u.id = :userId")
    public UserPermissions getByUserId(@Param("userId") Long userId);

    @Query("SELECT up FROM UserPermissions up left join up.user u left join up.course c where u.id = :userId")
    public List<UserPermissions> getAllByUserId(@Param("userId") Long userId);

}
