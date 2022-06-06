package ru.edu.space.app.service.validation;


import ru.edu.space.app.entity.user.User;

/**
 * @author agerus
 */
public interface UserValidationService {

    public void validate(User validateUser, String action);

    public void validateBeforeRegister(User validateUser);

}
