package ru.edu.space.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.service.PrincipalService;
import ru.edu.space.app.service.user.UserService;

import java.security.Principal;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

/**
 * Base controller for all controllers
 *
 * @param <T>
 */
public abstract class AbstractController<T> {

    protected static final String ID_KEY = "id";

    @Autowired
    protected UserService userService;

    @Autowired
    private PrincipalService principalService;


    /**
     * For override if needed
     * Using for change return object.
     * For example for set some fields to null (jackson recursion reasons) or add additional data fields
     *
     * @param entity T
     * @return T
     */
    protected T customModificationFieldsForSerialization(T entity) {
        return entity;
    }

    /**
     * For override if needed
     * Using for change return object.
     * For example for set some fields to null (jackson recursion reasons) or add additional data fields
     *
     * @param collection collection
     * @return T
     */
    protected Collection<T> customModificationFieldsForSerialization(Collection<T> collection) {
        return collection.stream()
                .map(this::customModificationFieldsForSerialization)
                .collect(toList());
    }

    public User me(Principal principal) {
        return principalService.getUser(principal);
    }
}
