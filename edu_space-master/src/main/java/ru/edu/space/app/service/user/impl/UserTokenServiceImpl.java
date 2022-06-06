package ru.edu.space.app.service.user.impl;

import org.springframework.stereotype.Service;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.entity.user.UserToken;
import ru.edu.space.app.exception.NotFoundException;
import ru.edu.space.app.repository.user.UserTokenDao;
import ru.edu.space.app.service.user.UserTokenService;

@Service
public class UserTokenServiceImpl implements UserTokenService {

    private final UserTokenDao userTokenDao;

    public UserTokenServiceImpl(UserTokenDao userTokenDao) {
        this.userTokenDao = userTokenDao;
    }

    @Override
    public void createToken(User user, String token) {
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken.setUser(user);
        userTokenDao.save(userToken);
    }

    @Override
    public User getByToken(String token) {
        UserToken userToken = userTokenDao.getByToken(token);
        if (userToken == null) {
            throw new NotFoundException("Token not be found");
        }
        return userToken.getUser();
    }


}
