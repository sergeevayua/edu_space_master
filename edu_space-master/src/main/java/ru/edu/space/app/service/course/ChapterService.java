package ru.edu.space.app.service.course;

import ru.edu.space.app.entity.course.Chapter;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.repository.course.ChapterDao;
import ru.edu.space.app.service.BaseEntityService;

import java.util.List;

/**
 * @author agerus
 */
public interface ChapterService extends BaseEntityService<Long, Chapter, ChapterDao> {
    List<Chapter> getAllByModuleId(Long moduleId, User user);
}
