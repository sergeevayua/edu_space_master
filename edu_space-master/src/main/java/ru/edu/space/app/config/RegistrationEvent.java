package ru.edu.space.app.config;

import org.springframework.context.ApplicationEvent;
import ru.edu.space.app.entity.user.User;

/**
 * @author agerus
 */
public class RegistrationEvent extends ApplicationEvent {

    private final User user;
    private final String appUrl;

    public RegistrationEvent(User user, String appUrl) {
        super(user);
        this.user = user;
        this.appUrl = appUrl;
    }

    public User getUser() {
        return user;
    }

    public String getAppUrl() {
        return appUrl;
    }
}
