package ru.edu.space.app.exception;

/**
 * @author agerus
 */
public class DuplicateException extends RuntimeException {

    public DuplicateException(String message) {
        super(message);
    }
}
