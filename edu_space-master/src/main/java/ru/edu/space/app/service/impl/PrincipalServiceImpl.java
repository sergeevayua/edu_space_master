package ru.edu.space.app.service.impl;


import org.springframework.stereotype.Service;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.service.PrincipalService;
import ru.edu.space.app.service.user.UserService;

import java.security.Principal;
/**
 * @author agerus
 */
@Service
public class PrincipalServiceImpl implements PrincipalService {

    private final UserService userService;

    public PrincipalServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User getUser(Principal principal) {
        if (principal instanceof org.springframework.security.core.userdetails.User) {
            return userService.getUserByUsername(((org.springframework.security.core.userdetails.User) principal).getUsername());
        } else {
            return userService.getUserByUsername(principal.getName());
        }
    }
}
