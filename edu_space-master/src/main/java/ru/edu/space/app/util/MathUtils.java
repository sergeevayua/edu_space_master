package ru.edu.space.app.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class MathUtils {

    /**
     * @param obj obj
     * @return zero or not
     */
    public static boolean isZero(Object obj) {
        if (obj == null) {
            return false;
        }

        String str = String.valueOf(obj);
        try {
            return Double.parseDouble(str) == 0.;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Round a float number. Default scale = 2
     * @param value input value
     * @return rounded value
     */
    public static double round (Double value) {
       return new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

    /**
     * Round a float number
     *
     * @param value input value
     * @param numbersAfterDot scale
     * @return rounded value
     * @throws NullPointerException if passed null value
     */
    public static double round(Double value, int numbersAfterDot) {
        Objects.requireNonNull(value, "Cannot be passed null for value");
        return new BigDecimal(value).setScale(numbersAfterDot, RoundingMode.HALF_EVEN).doubleValue();
    }

    /**
     * If passed null value - return null
     *
     * @param value value
     * @param numbersAfterDot numbersAfterDot
     * @return round
     */
    public static Double roundNullable(Double value, int numbersAfterDot) {
        if (value == null) {
            return null;
        }
        return round(value, numbersAfterDot);
    }
}
