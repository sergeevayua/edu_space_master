package ru.edu.space.app.controller.main;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.space.app.controller.AbstractLoginController;
import ru.edu.space.app.security.jwt.JWTTokenFilter;
import ru.edu.space.app.security.jwt.support.JWTDto;
import ru.edu.space.app.service.user.UserService;

/**
 * @author agerus
 */
@RestController
public class LoginController extends AbstractLoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<JWTDto> login(@RequestBody String json) {
        JWTDto jwt = userService.getJWTToken(json);
        HttpHeaders httpHeaders = getLoginHeader(jwt);

        return new ResponseEntity<>(jwt, httpHeaders, HttpStatus.OK);
    }

}
