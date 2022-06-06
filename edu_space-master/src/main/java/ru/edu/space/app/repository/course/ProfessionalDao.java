package ru.edu.space.app.repository.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.space.app.entity.course.Professional;

/**
 * @author agerus
 */
@Repository
public interface ProfessionalDao extends JpaRepository<Professional, Long> {
}
