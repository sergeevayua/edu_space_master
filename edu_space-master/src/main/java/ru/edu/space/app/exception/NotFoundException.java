package ru.edu.space.app.exception;

/**
 * @author agerus
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String msg) {
        super(msg);
    }
}
