package ru.edu.space.app.service.impl;

import org.springframework.stereotype.Service;
import ru.edu.space.app.exception.NotFoundException;
import ru.edu.space.app.service.RouteParamsValidationRuleService;
import ru.edu.space.app.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author agerus
 */
@Service
public class RouteParamsValidationRuleServiceImpl implements RouteParamsValidationRuleService {


    @Override
    public Long getId(Object[] routeParams, int index, String exceptionMsg) {
        List<Object> paramList = Arrays.asList(routeParams);

        if (!CollectionUtils.indexExists(paramList, index)) {
            throw new NotFoundException(exceptionMsg);
        }

        return convertToLong(paramList.get(index));
    }

    private Long convertToLong(Object o){
        String stringToConvert = String.valueOf(o);
        return Long.parseLong(stringToConvert);
    }
}
