package ru.edu.space.app.service;

/**
 * @author agerus
 */
public interface RouteParamsValidationRuleService {

    public Long getId(Object[] routeParams, int index, String exceptionMsg);
}
