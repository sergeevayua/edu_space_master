package ru.edu.space.app.controller;

import org.springframework.http.HttpHeaders;
import ru.edu.space.app.security.jwt.JWTTokenFilter;
import ru.edu.space.app.security.jwt.support.JWTDto;

/**
 * @author agerus
 */
public abstract class AbstractLoginController<T> extends AbstractController<T> {


    protected HttpHeaders getLoginHeader(JWTDto jwt) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTTokenFilter.AUTHORIZATION_HEADER, "Bearer ".concat(jwt.getTokenId()));
        return httpHeaders;
    }
}
