package ru.edu.space.app.service.validation.impl;

import org.springframework.stereotype.Service;
import ru.edu.space.app.exception.NotFoundException;
import ru.edu.space.app.service.validation.NotNullFieldValidateService;

/**
 * @author agerus
 */
@Service
public class NotNullFieldValidateServiceImpl implements NotNullFieldValidateService {

    @Override
    public void validate(Object field, String fieldName) {
        if (field != null) {
            return;
        }

        String msg = String.format("Field name: '%s' cannot be null", fieldName);
        throw new NotFoundException(msg);
    }

    @Override
    public void validateNotNullObject(Object object, String msg) {
        if (object == null)
            throw new NotFoundException(msg);
    }
}
