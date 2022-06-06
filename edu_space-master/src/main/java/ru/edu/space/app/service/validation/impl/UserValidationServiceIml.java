package ru.edu.space.app.service.validation.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.exception.DuplicateException;
import ru.edu.space.app.repository.user.UserDao;
import ru.edu.space.app.service.EnumAction;
import ru.edu.space.app.service.validation.NotNullFieldValidateService;
import ru.edu.space.app.service.validation.UserValidationService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author agerus
 */
@Service
public class UserValidationServiceIml implements UserValidationService {

    private final UserDao userDao;

    private final NotNullFieldValidateService notNullFieldValidateService;

    @Autowired
    public UserValidationServiceIml(UserDao userDao, NotNullFieldValidateService notNullFieldValidateService) {
        this.userDao = userDao;
        this.notNullFieldValidateService = notNullFieldValidateService;
    }

    @Override
    public void validate(User validateUser, String action) {
        notNullFieldValidateService.validate(validateUser.getFirstName(), "firstName");
        notNullFieldValidateService.validate(validateUser.getLastName(), "lastName");
        notNullFieldValidateService.validate(validateUser.getAge(), "age");
        notNullFieldValidateService.validate(validateUser.getLogin(), "login");
        notNullFieldValidateService.validate(validateUser.getPassword(), "password");
        notNullFieldValidateService.validate(validateUser.getEmail(), "email");
        notNullFieldValidateService.validate(validateUser.getPhone(), "phone");

        if (action.equalsIgnoreCase(EnumAction.CREATE.toString())) {
            validateLogin(validateUser);
            validateEmail(validateUser);
            validatePhone(validateUser);
        }
        if (action.equalsIgnoreCase(EnumAction.UPDATE.toString())) {
            validateUpdateEmail(validateUser);
            validateUpdatePhone(validateUser);
            validateUpdateLogin(validateUser);
        }
    }

    @Override
    public void validateBeforeRegister(User validateUser) {
        validateEmail(validateUser);
    }

    private void validateLogin(User validateUser) {
        String login = validateUser.getLogin();
        User user = getDao().getUserByLogin(login);

        if (user != null) {
            String message = String.format("User with this login = '%s' already exist", login);
            throw new DuplicateException(message);
        }
    }

    private void validatePhone(User validateUser) {
        phoneNumberValidation(validateUser);
        String phone = validateUser.getPhone();
        User user = getDao().getUserByPhone(phone);

        if (user != null) {
            String message = String.format("User with this phone = '%s' already exist", phone);
            throw new DuplicateException(message);
        }
    }

    private void validateEmail(User validateUser) {
        String email = validateUser.getEmail();
        User user = getDao().getUserByLogin(email);

        if (user != null) {
            String message = String.format("User with this email = '%s' already exist", email);
            throw new DuplicateException(message);
        }
    }

    private void validateUpdateLogin(User validateUser) {
        String validateUserLogin = validateUser.getLogin();
        User dbUser = getDao().getOne(validateUser.getId());
        String dbUserLogin = dbUser.getLogin();
        if (validateUserLogin != dbUserLogin) {
            User user = getDao().getUserByLogin(validateUserLogin);
            if (user != null) {
                String message = String.format("Login = '%s' already use ", validateUserLogin);
                throw new DuplicateException(message);
            }
        }
    }

    private void validateUpdateEmail(User validateUser) {
        String validateUserEmail = validateUser.getEmail();
        User dbUser = getDao().getOne(validateUser.getId());
        String dbUserEmail = dbUser.getEmail();
        if (validateUserEmail != dbUserEmail) {
            User user = getDao().getUserByEmail(validateUserEmail);
            if (user != null) {
                String message = String.format("email = '%s' already use ", validateUserEmail);
                throw new DuplicateException(message);
            }
        }
    }

    private void validateUpdatePhone(User validateUser) {
        String validateUserPhone = validateUser.getPhone();
        User dbUser = getDao().getOne(validateUser.getId());
        String dbUserPhone = dbUser.getPhone();
        if (validateUserPhone != dbUserPhone) {
            User user = getDao().getUserByPhone(validateUserPhone);
            if (user != null) {
                String message = String.format("phone = '%s' already use ", validateUserPhone);
                throw new DuplicateException(message);
            }
        }
    }

    private UserDao getDao() {
        return userDao;
    }

    private void phoneNumberValidation(User user) {
        String phoneNumber = user.getPhone();
        Pattern pattern = Pattern.compile("^((8|\\+7|7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            if (phoneNumber.startsWith("8")) {
                phoneNumber = phoneNumber.replaceFirst("8", "7");
            }
            user.setPhone(phoneNumber.replaceAll("[+()-]", "")
                    .replaceAll("\\s", ""));
        }
    }
}