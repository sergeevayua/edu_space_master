package ru.edu.space.app.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.space.app.controller.AbstractController;
import ru.edu.space.app.entity.user.Role;
import ru.edu.space.app.service.user.RoleService;

import java.security.Principal;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author agerus
 */
@RestController
@RequestMapping(RoleController.PATH)
public class RoleController extends AbstractController<Role> {

    public static final String PATH = "/api/role/";

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "", method = GET, produces = APPLICATION_JSON_VALUE)
    public Collection<Role> getAll(Principal principal) {
        Collection<Role> collection = roleService.getAll(me(principal));
        return customModificationFieldsForSerialization(collection);
    }

    @RequestMapping(value = "{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public Role getOne(@PathVariable Long id, Principal principal) {
        Role item = roleService.getById(id);
        return customModificationFieldsForSerialization(item);
    }
}
