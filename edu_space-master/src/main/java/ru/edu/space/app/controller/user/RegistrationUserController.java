package ru.edu.space.app.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.space.app.config.RegistrationComponent;
import ru.edu.space.app.config.RegistrationEvent;
import ru.edu.space.app.controller.AbstractLoginController;
import ru.edu.space.app.entity.user.User;
import ru.edu.space.app.security.jwt.JWTTokenFilter;
import ru.edu.space.app.security.jwt.support.JWTDto;
import ru.edu.space.app.service.user.UserService;
import ru.edu.space.app.service.user.UserTokenService;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * @author agerus
 */

@RestController
@RequestMapping(RegistrationUserController.PATH)
public class RegistrationUserController extends AbstractLoginController<User> {

    public static final String PATH = "/registration/";

    private final UserService userService;

    private final RegistrationComponent registrationComponent;

    private final UserTokenService userTokenService;

    @Autowired
    public RegistrationUserController(UserService userService, RegistrationComponent registrationComponent,
                                      UserTokenService userTokenService) {
        this.userService = userService;
        this.registrationComponent = registrationComponent;
        this.userTokenService = userTokenService;
    }

    @RequestMapping(value = "", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTDto> registerUser(@RequestBody String json, final HttpServletRequest request) {
        User user = userService.registerUser(json);
        JWTDto jwt = userService.getJWTToken(json);
        HttpHeaders httpHeaders = getLoginHeader(jwt);
        userService.activateUser(user);
//        registrationComponent.onApplicationEvent(new RegistrationEvent(user, getAppUrl(request)));
        return new ResponseEntity<>(jwt, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/registration-confirm", method = GET, produces = APPLICATION_JSON_VALUE)
    private User confirmRegistration(@RequestParam String token) {
        User user = userTokenService.getByToken(token);
        userService.activateUser(user);
        return customModificationFieldsForSerialization(user);
    }

    private String getAppUrl(HttpServletRequest request) {
        return new StringBuilder("http://")
                .append(request.getServerName()).append(":").append(request.getServerPort()).append(request.getContextPath()).toString();
    }
}
