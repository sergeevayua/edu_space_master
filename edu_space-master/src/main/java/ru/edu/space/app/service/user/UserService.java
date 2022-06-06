package ru.edu.space.app.service.user;


import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.repository.user.UserDao;
import ru.edu.space.app.security.jwt.support.JWTDto;
import ru.edu.space.app.service.BaseEntityService;

/**
 * @author agerus
 */
public interface UserService extends BaseEntityService<Long, User, UserDao> {

    User getUserByEmailOrPhoneOrLogin(String email, String phone, String login);

    User getUserByUsername(String userName);

    User createUser(String json, User user, Object... params);

    User updateUser(Long id, String json, User user, Object... params);

    void removeUser(Long id, User user, Object... params);

    User registerUser(String json);

    User resetPassword(String password, Long id, User user);

    void activateUser(User user);

    boolean getUserByLoginAndPassword(String login, String password);

    JWTDto getJWTToken(String json);
}
