package ru.edu.space.app.service.course;

import ru.edu.space.app.entity.course.CourseGroup;
import ru.edu.space.app.entity.course.support.ChartDto;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.repository.course.CourseGroupDao;
import ru.edu.space.app.service.BaseEntityService;

import java.util.List;

/**
 * @author agerus
 */
public interface CourseGroupService extends BaseEntityService<Long, CourseGroup, CourseGroupDao> {
    List<CourseGroup> getAllByProfessionalId(Long professionalId, User user);

    ChartDto getAggregatedChartDataByCourse(User user);
}
