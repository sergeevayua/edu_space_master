package ru.edu.space.app.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.space.app.controller.AbstractController;
import ru.edu.space.app.entity.course.Chapter;
import ru.edu.space.app.entity.course.Course;
import ru.edu.space.app.entity.course.Module;
import ru.edu.space.app.service.course.ChapterService;

import java.security.Principal;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author agerus
 */
@RestController
@RequestMapping(ChapterController.PATH)
public class ChapterController extends AbstractController<Chapter> {

    public static final String PATH = "/api/module/{moduleId}/chapter/";

    private final ChapterService chapterService;

    @Autowired
    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @RequestMapping(value = "/", method = GET, produces = APPLICATION_JSON_VALUE)
    public Collection<Chapter> getAll(@PathVariable Long moduleId, Principal principal) {
        Collection<Chapter> collection = chapterService.getAllByModuleId(moduleId, me(principal));
        return customModificationFieldsForSerialization(collection);
    }

    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public Chapter getOne(@PathVariable Long id, @PathVariable Long moduleId, Principal principal) {
        Chapter item = chapterService.getById(id, me(principal));
        return customModificationFieldsForSerialization(item);
    }

    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public Chapter create(@PathVariable Long moduleId, @RequestBody String body, Principal principal) {
        Chapter item = chapterService.create(body, me(principal), moduleId);
        return customModificationFieldsForSerialization(item);
    }

    @RequestMapping(value = "/{id}", method = PATCH, produces = APPLICATION_JSON_VALUE)
    public Chapter update(@PathVariable Long id, @PathVariable Long moduleId, @RequestBody String body, Principal principal) {
        Chapter item = chapterService.update(id, body, me(principal), moduleId);
        return customModificationFieldsForSerialization(item);
    }

    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id, @PathVariable Long moduleId, Principal principal) {
        chapterService.remove(id, me(principal), moduleId);
    }
}
