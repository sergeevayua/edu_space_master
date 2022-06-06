package ru.edu.space.app.repository.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.edu.space.app.entity.course.CourseGroup;

import java.util.List;
import java.util.Map;

/**
 * @author agerus
 */
@Repository
public interface CourseGroupDao extends JpaRepository<CourseGroup, Long> {
    @Query("SELECT courseGroup FROM CourseGroup courseGroup WHERE courseGroup.professional.id = :profId")
    public List<CourseGroup> getAllCourseGroupsByProfessionalId(@Param("profId") Long profId);

    @Query(value = "select course_group.name, count(c.id) from course_group join course c on course_group.id = c.course_group_id group by course_group_id",
            nativeQuery = true)
    public List<Object[]> getAggregatedChartData();
}
