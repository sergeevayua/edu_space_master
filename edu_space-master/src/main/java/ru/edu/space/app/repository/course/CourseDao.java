package ru.edu.space.app.repository.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.edu.space.app.entity.course.Course;
import ru.edu.space.app.entity.course.CourseGroup;
import ru.edu.space.app.entity.course.UserPermissions;

import java.util.List;
import java.util.Set;

/**
 * @author agerus
 */
@Repository
public interface CourseDao extends JpaRepository<Course, Long> {
    @Query("SELECT course FROM Course course WHERE course.courseGroup.id = :groupId")
    public List<Course> getAllCoursesByCourseGroupId(@Param("groupId") Long groupId);

//    @Query("SELECT course.id FROM UserPermissions permission JOIN permission.course course ON permission.user.id = :userId")
//    public List<Course> getAllCoursesByUserId(@Param("userId") Long userId);

    @Query("select p from UserPermissions p join p.permissions ps where ps = :permission")
    public List<Course> getAllCoursesByUserId(@Param("permission") String permission);
}
