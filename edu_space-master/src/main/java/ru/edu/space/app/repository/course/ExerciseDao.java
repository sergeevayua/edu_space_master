package ru.edu.space.app.repository.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.edu.space.app.entity.course.Exercise;
import ru.edu.space.app.entity.course.Module;

import java.util.List;

/**
 * @author agerus
 */
@Repository
public interface ExerciseDao extends JpaRepository<Exercise, Long> {

    @Query("SELECT exercise FROM Exercise exercise JOIN Chapter chapter WHERE chapter.id = :chapterId AND exercise.numeration = :numeration")
    public Exercise getByChapterAndNumeration(@Param("chapterId") Long chapterId, @Param("numeration") Long numeration);

    @Query("SELECT exercise FROM Exercise exercise WHERE exercise.chapter.id = :chapterId")
    public List<Exercise> getAllExercisesByChapterId(@Param("chapterId") Long chapterId);
}
