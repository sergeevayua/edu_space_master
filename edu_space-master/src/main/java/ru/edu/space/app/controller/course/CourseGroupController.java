package ru.edu.space.app.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.space.app.controller.AbstractController;
import ru.edu.space.app.controller.AbstractRestController;
import ru.edu.space.app.entity.course.CourseGroup;
import ru.edu.space.app.entity.course.support.ChartDto;
import ru.edu.space.app.security.jwt.support.JWTDto;
import ru.edu.space.app.service.EntityService;
import ru.edu.space.app.service.course.CourseGroupService;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author agerus
 */
@RestController
@RequestMapping(CourseGroupController.PATH)
public class CourseGroupController extends AbstractController<CourseGroup> {


    public static final String PATH = "/api/professional/{professionalId}/course-group/";

    private final CourseGroupService courseGroupService;

    @Autowired
    public CourseGroupController(CourseGroupService courseGroupService) {
        this.courseGroupService = courseGroupService;
    }

    @RequestMapping(value = "/", method = GET, produces = APPLICATION_JSON_VALUE)
    public Collection<CourseGroup> getAllByProfessionalId(@PathVariable Long professionalId, Principal principal) {
        Collection<CourseGroup> collection = courseGroupService.getAllByProfessionalId(professionalId, me(principal));
        return customModificationFieldsForSerialization(collection);
    }

    @RequestMapping(value = "/aggregated-by-course", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ChartDto> getAggregatedChartDataByCourse(@PathVariable Long professionalId, Principal principal) {
        ChartDto chartDto = courseGroupService.getAggregatedChartDataByCourse(me(principal));
        return new ResponseEntity<>(chartDto, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public CourseGroup getOne(@PathVariable Long id, @PathVariable Long professionalId, Principal principal) {
        CourseGroup item = courseGroupService.getById(id, me(principal));
        return customModificationFieldsForSerialization(item);
    }

    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public CourseGroup create(@PathVariable Long professionalId, @RequestBody String body, Principal principal) {
        CourseGroup item = courseGroupService.create(body, me(principal), professionalId);
        return customModificationFieldsForSerialization(item);
    }

    @RequestMapping(value = "/{id}", method = PATCH, produces = APPLICATION_JSON_VALUE)
    public CourseGroup update(@PathVariable Long id, @PathVariable Long professionalId, @RequestBody String body, Principal principal) {
        CourseGroup item = courseGroupService.update(id, body, me(principal), professionalId);
        return customModificationFieldsForSerialization(item);
    }

    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id, @PathVariable Long professionalId, Principal principal) {
        courseGroupService.remove(id, me(principal), professionalId);
    }

}
