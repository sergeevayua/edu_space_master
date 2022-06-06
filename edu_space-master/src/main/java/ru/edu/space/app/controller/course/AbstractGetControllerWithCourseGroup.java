package ru.edu.space.app.controller.course;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.edu.space.app.controller.AbstractController;
import ru.edu.space.app.entity.BaseEntity;
import ru.edu.space.app.service.EntityService;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author agerus
 */
public abstract class AbstractGetControllerWithCourseGroup<PK extends Serializable, T extends BaseEntity> extends AbstractController<T> {

    protected abstract EntityService<PK, T> getService();

    @RequestMapping(value = "/", method = GET, produces = APPLICATION_JSON_VALUE)
    public Collection<T> getAll(@PathVariable Long courseGroupId, Principal principal) {
        Collection<T> collection = getService().getAll(me(principal));
        return customModificationFieldsForSerialization(collection);
    }

    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public T getOne(@PathVariable PK id, @PathVariable Long courseGroupId, Principal principal) {
        T item = getService().getById(id, me(principal));
        return customModificationFieldsForSerialization(item);
    }
}
