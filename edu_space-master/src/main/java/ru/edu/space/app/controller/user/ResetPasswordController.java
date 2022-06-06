package ru.edu.space.app.controller.user;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.space.app.controller.AbstractController;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.service.user.UserService;

import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;

/**
 * @author agerus
 */
@RestController
@RequestMapping(ResetPasswordController.PATH)
public class ResetPasswordController extends AbstractController<User> {

    public static final String PATH = "/reset-password/";

    private final UserService userService;

    public ResetPasswordController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/userId/{userId}", method = PATCH, produces = APPLICATION_JSON_VALUE)
    public User resetPassword(@RequestBody String password, @PathVariable Long userId, Principal principal) {
        User user = userService.resetPassword(password, userId, me(principal));
        return customModificationFieldsForSerialization(user);
    }
}
