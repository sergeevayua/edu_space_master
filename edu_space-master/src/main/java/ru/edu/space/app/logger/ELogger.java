package ru.edu.space.app.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author agerus
 */
public class ELogger implements IELog {

    private static Logger logger;

    private static IELog instance;

    private ELogger() {

    }

    public static IELog getLogger(Class<?> clazz) {
        String className = clazz.getName();
        int idx = className.lastIndexOf('.');
        logger = idx < 0 ? LogManager.getLogger("") : LogManager.getLogger(className.substring(0, idx));
        return getInstance();
    }

    @Override
    public void info(Object... o) {
        logger.info(concat(o));
    }

    @Override
    public void info(String s, Throwable t) {
        logger.info(s, t);
    }

    @Override
    public void error(Object... o) {
        logger.error(concat(o));
    }

    @Override
    public void error(String s, Throwable t) {
        logger.error(s, t);
    }

    @Override
    public void warn(Object... o) {
        logger.warn(concat(o));
    }

    @Override
    public void warn(String s, Throwable t) {
        logger.warn(s, t);
    }

    private static String concat(Object[] objs) {
        switch (objs.length) {
            case 0:
                return "";
            case 1:
                return String.valueOf(objs[0]);
            default:


                StringBuilder res = new StringBuilder();
                for (Object x : objs) {

                    if (x instanceof Object[])
                        res.append(concat((Object[]) x));
                    else
                        res.append(x);
                }
                return res.toString();
        }
    }

    private static synchronized IELog getInstance() {
        if (instance == null) {
            instance = new ELogger();
        }
        return instance;
    }
}
