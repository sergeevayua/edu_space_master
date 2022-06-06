package ru.edu.space.app.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.space.app.entity.course.Course;
import ru.edu.space.app.entity.course.CourseGroup;
import ru.edu.space.app.service.EntityService;
import ru.edu.space.app.service.course.CourseService;

import java.security.Principal;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author agerus
 */
@RestController
@RequestMapping(CourseController.PATH)
public class CourseController extends AbstractRestControllerWithCourseGroup<Long, Course> {

    public static final String PATH = "/api/course-group/{courseGroupId}/course/";

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    protected EntityService<Long, Course> getService() {
        return courseService;
    }

    @RequestMapping(value = "/", method = GET, produces = APPLICATION_JSON_VALUE)
    public Collection<Course> getAll(@PathVariable Long courseGroupId, Principal principal) {
        Collection<Course> collection = courseService.getAllByCourseGroupId(courseGroupId, me(principal));
        return customModificationFieldsForSerialization(collection);
    }

    @RequestMapping(value = "/filtered", method = GET, produces = APPLICATION_JSON_VALUE)
    public Collection<Course> getAllByUserId(@PathVariable Long courseGroupId, Principal principal) {
        Collection<Course> collection = courseService.getAllByUserId(me(principal));
        return customModificationFieldsForSerialization(collection);
    }

    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public Course create(@PathVariable Long courseGroupId, @RequestBody String body, Principal principal) {
        Course item = courseService.create(body, me(principal), courseGroupId);
        return customModificationFieldsForSerialization(item);
    }
}
