package ru.edu.space.app.service.course;

import ru.edu.space.app.entity.course.Exercise;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.repository.course.ExerciseDao;
import ru.edu.space.app.service.BaseEntityService;

import java.util.List;

/**
 * @author agerus
 */
public interface ExerciseService extends BaseEntityService<Long, Exercise, ExerciseDao> {
    List<Exercise> getAllByChapterId(Long moduleId, User user);
}
