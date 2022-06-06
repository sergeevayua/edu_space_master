package ru.edu.space.app.service.validation;

/**
 * Validation for get ids passed from route
 * Ex.: /api/event/2/material/ - try get eventId from routeParams = [2]
 *
 * @param <T> type id from route
 */
public interface RouteParamsValidationRule<T> {

    public T getId(Object[] routeParams, int index, String exceptionMsg);
}
