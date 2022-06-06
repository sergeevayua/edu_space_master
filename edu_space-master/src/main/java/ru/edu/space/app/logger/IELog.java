package ru.edu.space.app.logger;

/**
 * @author agerus
 */
public interface IELog {

    void info(Object... o);
    void info(String s, Throwable t);

    void error(Object... o);
    void error(String s, Throwable t);

    void warn(Object... o);
    void warn(String s, Throwable t);

}
