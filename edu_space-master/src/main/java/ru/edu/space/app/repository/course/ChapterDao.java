package ru.edu.space.app.repository.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.edu.space.app.entity.course.Chapter;
import ru.edu.space.app.entity.course.Course;

import java.util.List;

/**
 * @author agerus
 */
@Repository
public interface ChapterDao extends JpaRepository<Chapter, Long> {
    @Query("SELECT chapter FROM Chapter chapter WHERE chapter.module.id = :moduleId")
    public List<Chapter> getAllChaptersByModuleId(@Param("moduleId") Long moduleId);
}
