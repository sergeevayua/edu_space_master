package ru.edu.space.app.service.validation.impl;


import org.springframework.stereotype.Service;
import ru.edu.space.app.exception.NotFoundException;
import ru.edu.space.app.service.validation.RouteParamsValidationRule;
import ru.edu.space.app.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class RouteParamsValidationRuleImpl<T> implements RouteParamsValidationRule<T> {

    @Override
    @SuppressWarnings("unchecked")
    public T getId(Object[] routeParams, int index, String exceptionMsg) {
        List<Object> paramList = Arrays.asList(routeParams);

        if (!CollectionUtils.indexExists(paramList, index)) {
            throw new NotFoundException(exceptionMsg);
        }

        return (T) paramList.get(index);
    }
}
