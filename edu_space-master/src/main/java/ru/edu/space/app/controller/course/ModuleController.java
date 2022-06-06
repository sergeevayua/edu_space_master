package ru.edu.space.app.controller.course;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.space.app.entity.course.Course;
import ru.edu.space.app.entity.course.Module;
import ru.edu.space.app.service.EntityService;
import ru.edu.space.app.service.course.ModuleService;

import java.security.Principal;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author agerus
 */
@RestController
@RequestMapping(ModuleController.PATH)
public class ModuleController extends AbstractRestControllerWithCourse<Long, Module> {

    public static final String PATH = "/api/course-group/{courseGroupId}/course/{courseId}/module/";

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @Override
    protected EntityService<Long, Module> getService() {
        return moduleService;
    }

    @RequestMapping(value = "/", method = GET, produces = APPLICATION_JSON_VALUE)
    public Collection<Module> getAll(@PathVariable Long courseGroupId, @PathVariable Long courseId, Principal principal) {
        Collection<Module> collection = moduleService.getAllByCourseGroupIdAndCourseId(courseGroupId, courseId, me(principal));
        return customModificationFieldsForSerialization(collection);
    }
}
