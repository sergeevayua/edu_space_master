package ru.edu.space.app.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Utils class for convert checked exception to unchecked
 * This class does not wrap checked exceptions in RuntimeException. The type of exception remains the same.
 *
 * @see <a href="https://www.mail-archive.com/javaposse@googlegroups.com/msg05984.html"
 */
@SuppressWarnings("unchecked")
public class ExceptionUtils {

    public static <E extends Throwable> void sneakyThrow(Throwable exception) throws E {
        throw (E) exception;
    }

    public static RuntimeException throwAsUnchecked(Throwable exception) {
        sneakyThrow(exception);
        return null;
    }

    /**
     * @param ex ex
     * @return formatted stack trace
     */
    public static String stackTraceToString(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    /**
     * @param e e
     * @return string
     */
    public static String exceptionMsgAndStackTrace(Exception e) {
        StringBuilder builder = new StringBuilder();
        builder.append(" ");
        builder.append(e.getMessage());
        builder.append(System.lineSeparator());
        builder.append(stackTraceToString(e));
        builder.append(System.lineSeparator());
        return builder.toString();
    }
}
