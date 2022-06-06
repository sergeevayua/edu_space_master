package ru.edu.space.app.exception;

/**
 * @author agerus
 */
public class DeniedException extends RuntimeException {

    public DeniedException(String message) {
        super(message);
    }
}
