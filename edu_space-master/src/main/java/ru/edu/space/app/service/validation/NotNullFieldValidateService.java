package ru.edu.space.app.service.validation;

/**
 * @author agerus
 */
public interface NotNullFieldValidateService {

    public void validate(Object field, String fieldName);

    public void validateNotNullObject(Object object, String msg);
}
