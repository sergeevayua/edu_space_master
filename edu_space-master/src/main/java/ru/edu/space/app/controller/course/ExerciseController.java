package ru.edu.space.app.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.space.app.controller.AbstractController;
import ru.edu.space.app.entity.course.Chapter;
import ru.edu.space.app.entity.course.CourseGroup;
import ru.edu.space.app.entity.course.Exercise;
import ru.edu.space.app.service.course.ChapterService;
import ru.edu.space.app.service.course.ExerciseService;

import java.security.Principal;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

/**
 * @author agerus
 */
@RestController
@RequestMapping(ExerciseController.PATH)
public class ExerciseController extends AbstractController<Exercise> {

    public static final String PATH = "/api/chapter/{chapterId}/exercise/";

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @RequestMapping(value = "/", method = GET, produces = APPLICATION_JSON_VALUE)
    public Collection<Exercise> getAllByChapterId(@PathVariable Long chapterId, Principal principal) {
        Collection<Exercise> collection = exerciseService.getAllByChapterId(chapterId, me(principal));
        return customModificationFieldsForSerialization(collection);
    }

    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public Exercise getOne(@PathVariable Long id, @PathVariable Long chapterId, Principal principal) {
        Exercise item = exerciseService.getById(id, me(principal));
        return customModificationFieldsForSerialization(item);
    }

    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public Exercise create(@PathVariable Long chapterId, @RequestBody String body, Principal principal) {
        Exercise item = exerciseService.create(body, me(principal), chapterId);
        return customModificationFieldsForSerialization(item);
    }

    @RequestMapping(value = "/{id}", method = PATCH, produces = APPLICATION_JSON_VALUE)
    public Exercise update(@PathVariable Long id, @PathVariable Long chapterId, @RequestBody String body, Principal principal) {
        Exercise item = exerciseService.update(id, body, me(principal), chapterId);
        return customModificationFieldsForSerialization(item);
    }

    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id, @PathVariable Long chapterId, Principal principal) {
        exerciseService.remove(id, me(principal), chapterId);
    }

}
