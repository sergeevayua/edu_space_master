package ru.edu.space.app.util;


public enum TimeEnum {
    SEC_IN_MIN(Constants.SEC_IN_MIN),
    SEC_IN_HOUR(Constants.SEC_IN_HOUR),
    MSEC_IN_MIN(Constants.MSEC_IN_MIN),
    MIN_IN_DAY(Constants.MIN_IN_DAY),
    MIN_IN_HOUR(Constants.MIN_IN_HOUR),
    SEC_IN_DAY(Constants.SEC_IN_DAY),
    MSEC_IN_DAY(Constants.MSEC_IN_DAY);

    public static class Constants {
        public static final long SEC_IN_MIN = 60L;
        public static final long SEC_IN_HOUR = 60L * 60L;
        public static final long MSEC_IN_MIN = 60 * 1000L;
        public static final long MIN_IN_DAY = 24 * 60L;
        public static final long MIN_IN_HOUR = 60L;
        public static final long SEC_IN_DAY = 24 * 60 * 60L;
        public static final long MSEC_IN_DAY = 24 * 60 * 60 * 1000L;
    }

    private long value;

    TimeEnum(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
