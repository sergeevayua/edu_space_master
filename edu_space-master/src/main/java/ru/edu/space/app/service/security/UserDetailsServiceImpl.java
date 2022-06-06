package ru.edu.space.app.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.exception.DeniedException;
import ru.edu.space.app.logger.ELogger;
import ru.edu.space.app.logger.IELog;
import ru.edu.space.app.service.user.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author agerus
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String PREFIX = "[UserDetailsService] ";
    protected static IELog log = ELogger.getLogger(UserDetailsServiceImpl.class);

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info(PREFIX, "loadUserByUsername = ", userName);
        User user = userService.getUserByEmailOrPhoneOrLogin(userName, userName, userName);
        if (user == null) {
            log.warn(PREFIX, "User not found by email/login/phone, username: ", userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        log.info("Found user: ", userName);
        return createSpringSecurityUser(userName, user);
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
//        if (!user.isEnabled()) {
//            String msg = String.format("User '%s' was not activated", lowercaseLogin);
//            throw new DeniedException(msg);
//        }
        List<GrantedAuthority> grantedAuthorities = user.getRole().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getPermission()))
                .collect(Collectors.toList());


        return new org.springframework.security.core.userdetails.User(lowercaseLogin,
                user.getPassword(),
                grantedAuthorities);
    }

}
