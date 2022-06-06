package ru.edu.space.app.service.user;

import ru.edu.space.app.entity.user.User;

/**
 * @author agerus
 */
public interface UserTokenService {

    void createToken(User user, String token);

    User getByToken(String token);
}
