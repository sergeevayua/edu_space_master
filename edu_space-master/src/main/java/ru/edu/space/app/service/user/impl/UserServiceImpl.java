package ru.edu.space.app.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.edu.space.app.entity.user.Role;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.entity.user.support.RoleEnum;
import ru.edu.space.app.entity.user.support.UserDto;
import ru.edu.space.app.exception.DeniedException;
import ru.edu.space.app.exception.NotFoundException;
import ru.edu.space.app.logger.ELogger;
import ru.edu.space.app.logger.IELog;
import ru.edu.space.app.repository.user.UserDao;
import ru.edu.space.app.security.jwt.TokenProvider;
import ru.edu.space.app.security.jwt.support.JWTDto;
import ru.edu.space.app.service.AbstractBaseEntityService;
import ru.edu.space.app.service.EnumAction;
import ru.edu.space.app.service.access.AccessControlService;
import ru.edu.space.app.service.user.RoleService;
import ru.edu.space.app.service.user.UserService;
import ru.edu.space.app.service.validation.NotNullFieldValidateService;
import ru.edu.space.app.service.validation.UserValidationService;
import ru.edu.space.app.util.JacksonUtil;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author agerus
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractBaseEntityService<Long, User, UserDao>
        implements UserService {

    private static final String PREFIX = "[UserService] ";
    protected static IELog log = ELogger.getLogger(UserServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserDao userDao;
    private final UserValidationService userValidationService;
    private final NotNullFieldValidateService notNullFieldValidateService;
    private final RoleService roleService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AccessControlService accessControlService;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserValidationService userValidationService,
                           NotNullFieldValidateService notNullFieldValidateService,
                           RoleService roleService,
                           TokenProvider tokenProvider,
                           AuthenticationManagerBuilder authenticationManagerBuilder,
                           AccessControlService accessControlService) {
        this.userDao = userDao;
        this.userValidationService = userValidationService;
        this.notNullFieldValidateService = notNullFieldValidateService;
        this.roleService = roleService;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.accessControlService = accessControlService;
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public IELog getLogger() {
        return log;
    }

    @Override
    public User getUserByEmailOrPhoneOrLogin(String email, String phone, String login) {
        validateFieldsBeforeGetUser(email, phone, login);
        User user = getDao().getUserByEmailOrPhoneOrLogin(email, phone, login);
        notNullFieldValidateService.validateNotNullObject(user,
                String.format("User cannot found by email = '%s' or phone = '%s' or login = '%s'", email, phone, login));
        return user;
    }

    @Override
    public User createUser(String json, User user, Object... params) {
        log.info(PREFIX, "create new User");
        User entity = JacksonUtil.fromString(json, User.class);

        if (!entityIsAllowedCreate(entity, user))
            throwDeniedException(String.format("You haven't permission for create user with login = '%s'", entity.getLogin()));

        customLogicBeforeCreate(entity, user, params);
        getDao().saveAndFlush(entity);
        customLogicAfterCreate(entity, user, params);
        log.info(PREFIX, "User was created with id = ", entity.getId());
        return entity;
    }

    @Override
    public User updateUser(Long id, String json, User user, Object... params) {
        log.info(PREFIX, "Updating user with id = ", id);
        User entity = getById(id, user);
        notNullFieldValidateService.validateNotNullObject(entity, String.format("Not found user with id = '%s'", id));

        if (!entityIsAllowedUpdate(entity, user))
            throwDeniedException(String.format("You haven't permission for update user with id = '%s' and login = '%s'",
                    entity.getId(), entity.getLogin()));

        JacksonUtil.updateFromString(json, entity);
        customLogicBeforeUpdate(entity, user, params);
        getDao().save(entity);
        customLogicAfterUpdate(entity, user, params);
        log.info(PREFIX, "User was update with id = ", id);
        return entity;
    }

    @Override
    public void removeUser(Long id, User user, Object... params) {
        log.info(PREFIX, "user deleting with id", id);
        User entity = getById(id, user);
        notNullFieldValidateService.validateNotNullObject(entity, String.format("Not found user with id = '%s'", id));

        if (!entityIsAllowedDelete(entity, user))
            throwDeniedException(String.format("You haven't permission for delete user with id = '%s' and login = '%s'",
                    entity.getId(), entity.getLogin()));

        customLogicBeforeRemove(entity, user, params);
        getDao().deleteById(id);
        customLogicAfterRemove(entity, user, params);
        log.info(PREFIX, "user was delete with id", id);
    }

    @Override
    public User registerUser(String json) {
        UserDto userDto = JacksonUtil.fromString(json, UserDto.class);
        log.info(PREFIX, "register new User with login: ", userDto.getLogin());
        User user = new User();
        user.setEmail(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        Role role = roleService.getById(Long.valueOf(RoleEnum.USER.getRoleId()));
        notNullFieldValidateService.validateNotNullObject(role, String.format("Not found role with id = '%s'", RoleEnum.USER.getRoleId()));
        user.setRole(new ArrayList<>(Collections.singleton(role)));
        customLogicBeforeRegistration(user);
        getDao().save(user);
        log.info(PREFIX, "User was created with id = ", user.getId());
        return user;
    }

    @Override
    public User resetPassword(String password, Long id, User user) {
        log.info(PREFIX, "user wants to change password!");
        User entity = getById(id, user);
        notNullFieldValidateService.validateNotNullObject(entity, String.format("Not found user with id = '%s'", id));

        if (!entityIsAllowedUpdate(entity, user))
            throwDeniedException(String.format("You haven't permission for reset password in user with id = '%s' and login = '%s'",
                    entity.getId(), entity.getLogin()));

        entity.setPassword(password);
        encodePassword(entity);
        getDao().save(entity);
        log.info(PREFIX, "user changed password!");
        return entity;
    }

    @Override
    public boolean getUserByLoginAndPassword(String login, String password) {
        if (login == null || password == null) {
            String msg = String.format("property login or password was null!");
            log.error(PREFIX, msg);
            throw new NotFoundException(msg);
        }
        User user = getDao().getUserByLogin(login);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public void activateUser(User user) {
        user.setEnabled(true);
        getDao().saveAndFlush(user);
    }

    @Override
    public JWTDto getJWTToken(String json) {
        UserDto userDto = JacksonUtil.fromString(json, UserDto.class);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getLogin(), userDto.getPassword());
        String jwt = null;
        User user = getUserByEmailOrPhoneOrLogin(userDto.getLogin(), userDto.getLogin(), userDto.getLogin());
        try {
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            boolean rememberMe = (userDto.getRemember() == null) ? false : userDto.getRemember();
            jwt = tokenProvider.createToken(authentication, rememberMe);
        } catch (Exception e) {
            log.error(PREFIX, e);
        }
        return new JWTDto(jwt, user.getId(), user.getRole());
    }

    @Override
    public User getUserByUsername(String userName) {
        return getDao().getUserByEmailOrPhoneOrLogin(userName, userName, userName);
    }

    @Override
    protected UserDao getDao() {
        return userDao;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected void customLogicBeforeCreate(User entity, User user, Object... params) {
        validateUserRole(entity);
        validateFields(entity, EnumAction.CREATE.toString());
    }

    @Override
    protected void customLogicBeforeUpdate(User entity, User user, Object... params) {
        validateFields(entity, EnumAction.UPDATE.toString());
    }

    @Override
    protected void customLogicBeforeRemove(User entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterCreate(User entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterUpdate(User entity, User user, Object... params) {

    }

    @Override
    protected void customLogicAfterRemove(User entity, User user, Object... params) {

    }

    @Override
    protected boolean entityIsAllowedCreate(User entity, User user) {
        return accessControlService.allowedActionForAdmin(user);
    }

    @Override
    protected boolean entityIsAllowedUpdate(User entity, User user) {
        return entity.getId().equals(user.getId()) || accessControlService.allowedActionForAdmin(user);
    }

    @Override
    protected boolean entityIsAllowedDelete(User entity, User user) {
        return accessControlService.allowedActionForAdmin(user);
    }

    // TODO сделать правильные пермишны для гет юзер
    @Override
    protected boolean entityIsAllowedGet(User user) {
        return accessControlService.allowedActionForUser(user);
    }

    private void validateFieldsBeforeGetUser(String email, String phone, String login) {
        notNullFieldValidateService.validate(email, "email");
        notNullFieldValidateService.validate(phone, "phone");
        notNullFieldValidateService.validate(login, "login");
    }

    private void validateUserRole(User entity) {
        Role role = roleService.getById(Long.valueOf(RoleEnum.SUPERADMIN.getRoleId()));
        if (entity.getRole().contains(role))
            throwDeniedException("Can't create super admin!!!");
    }

    private void customLogicBeforeRegistration(User entity, Object... params) {
        validateUserRole(entity);
        userValidationService.validateBeforeRegister(entity);
        encodePassword(entity);
    }

    private void encodePassword(User user) {
        String password = user.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        user.setPassword(encodePassword);
    }

    private void validateFields(User user, String action) {
        userValidationService.validate(user, action);
    }

    private void throwDeniedException(String msg) {
        String message = String.format(msg);
        log.error(PREFIX, message);
        throw new DeniedException(message);
    }
}
