package ru.edu.space.app.service;



import ru.edu.space.app.entity.user.User;

import java.security.Principal;

public interface PrincipalService {

    /**
     * Using for convert from Spring Security Principal to own user {@link User}
     *
     * @param principal principal
     * @return current user
     */
    public User getUser(Principal principal);
}
