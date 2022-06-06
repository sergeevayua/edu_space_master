package ru.edu.space.app.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.space.app.controller.AbstractGetController;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.service.EntityService;
import ru.edu.space.app.service.user.UserService;

import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author agerus
 */
@RestController
@RequestMapping(UserController.PATH)
public class UserController extends AbstractGetController<Long, User> {

    public static final String PATH = "/api/user/";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = POST, produces = APPLICATION_JSON_VALUE)
    public User create(@RequestBody String body, Principal principal) {
        User item = userService.createUser(body, me(principal));
        return customModificationFieldsForSerialization(item);
    }

    @RequestMapping(value = "{id}", method = PATCH, produces = APPLICATION_JSON_VALUE)
    public User update(@PathVariable Long id, @RequestBody String body, Principal principal) {
        User item = userService.updateUser(id, body, me(principal));
        return customModificationFieldsForSerialization(item);
    }

    @RequestMapping(value = "{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id, Principal principal) {
        userService.removeUser(id, me(principal));
    }

    @Override
    protected EntityService<Long, User> getService() {
        return userService;
    }
}
