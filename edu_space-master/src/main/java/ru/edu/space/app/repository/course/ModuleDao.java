package ru.edu.space.app.repository.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.edu.space.app.entity.course.CourseGroup;
import ru.edu.space.app.entity.course.Module;

import java.util.List;

/**
 * @author agerus
 */
@Repository
public interface ModuleDao extends JpaRepository<Module, Long> {
    @Query("SELECT module FROM Module module WHERE module.course.id = :courseId")
    public List<Module> getAllModulesByCourse(@Param("courseId") Long courseId);
}
