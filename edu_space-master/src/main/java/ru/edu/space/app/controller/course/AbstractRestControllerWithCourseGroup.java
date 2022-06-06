package ru.edu.space.app.controller.course;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.edu.space.app.entity.BaseEntity;

import java.io.Serializable;
import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author agerus
 */
public abstract class AbstractRestControllerWithCourseGroup<PK extends Serializable, T extends BaseEntity>
        extends AbstractGetControllerWithCourseGroup<PK, T> {

    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public T create(@PathVariable Long courseGroupId, @RequestBody String body, Principal principal) {
        T item = getService().create(body, me(principal), courseGroupId);
        return customModificationFieldsForSerialization(item);
    }

    @RequestMapping(value = "/{id}", method = PATCH, produces = APPLICATION_JSON_VALUE)
    public T update(@PathVariable PK id, @PathVariable Long courseGroupId, @RequestBody String body, Principal principal) {
        T item = getService().update(id, body, me(principal), courseGroupId);
        return customModificationFieldsForSerialization(item);
    }

    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public void delete(@PathVariable PK id, @PathVariable Long courseGroupId, Principal principal) {
        getService().remove(id, me(principal), courseGroupId);
    }

}
