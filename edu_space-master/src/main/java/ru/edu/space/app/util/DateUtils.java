package ru.edu.space.app.util;

import org.joda.time.Interval;

import org.springframework.util.StringUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.requireNonNull;

/**
 * Important! Convert to timestamp from timezone: {@link DateUtils#DEFAULT_TIME_ZONE_STRING}
 *
 * Useful for convert Date to Local and vice versa
 */
public class DateUtils {

    public static final Long UNKNOWN_FINISH_DATE = 3539948789000L; // 5 March 2082 г., 15:06:29

    public static final Long UNKNOWN_START_DATE = 1000L;

    public static final String DEFAULT_TIME_ZONE_STRING = "UTC";    // change this if need (Ex.: "Europe/Moscow")
                                                                    // Using for global set in Application
    public static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone(DEFAULT_TIME_ZONE_STRING);

    public static final ZoneOffset DEFAULT_ZONE_OFFSET;


    static {
        int rawOffset = DEFAULT_TIME_ZONE.getRawOffset();
        long offsetInHours = TimeUnit.HOURS.convert(rawOffset, TimeUnit.MILLISECONDS);

        DEFAULT_ZONE_OFFSET = ZoneOffset.ofHours((int)offsetInHours);
    }

    public static boolean isNextDay(Date start, Date next) {
        requireNonNull(start, "Cannot passed null start date");
        requireNonNull(next, "Cannot passed null next date");

        return !getBeginCurrDay(start).equals(getBeginCurrDay(next));
    }

    /**
     * @param minute minute
     * @return days
     */
    public static Double convertMinuteToDays(Double minute) {
        if (minute == null) {
            return null;
        }
        return minute / (double) TimeEnum.MIN_IN_DAY.getValue();
    }

    /**
     * @param sec sec
     * @return minutes
     */
    public static Double convertSecToMin(Long sec) {
        if (sec == null) {
            return null;
        }
        return sec / (double) TimeEnum.SEC_IN_MIN.getValue();
    }

    /**
     * @param sec sec
     * @return minutes
     */
    public static Double convertSecToHour(Long sec) {
        if (sec == null) {
            return null;
        }
        return sec / (double) TimeEnum.SEC_IN_HOUR.getValue();
    }

    /**
     * @param min min
     * @return minutes
     */
    public static Double convertMinToHour(Double min) {
        if (min == null) {
            return null;
        }
        return min / (double) TimeEnum.MIN_IN_HOUR.getValue();
    }

    /**
     * @param sec sec
     * @return minutes
     */
    public static Double convertSecToDay(Long sec) {
        if (sec == null) {
            return null;
        }
        return sec / (double) TimeEnum.SEC_IN_DAY.getValue();
    }

    /**
     * @param uDate uDate
     * @return java.sql.Date
     */
    public static java.sql.Date convertUtilToSql(Date uDate) {
        return new java.sql.Date(uDate.getTime());
    }

    /**
     * @param sDate sDate
     * @return java.util.Date
     */
    public static Date convertSqlToUtil(java.sql.Date sDate) {
        return new Date(sDate.getTime());
    }

    /**
     * With included boundary dates
     *
     * @param date     date
     * @param dateFrom dateFrom
     * @param dateTo   dateTo
     * @return true or false
     */
    public static boolean isBetween(Date date, Date dateFrom, Date dateTo) {
        return (date.after(dateFrom) && date.before(dateTo)) || date.equals(dateFrom) || date.equals(dateTo);
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(DEFAULT_ZONE_OFFSET).toInstant());
    }

    public static Date asDateFromUTC(Date date) {
        if (date == null) return null;
        return asDateFromUTC(asLocalDateTime(date));
    }

    public static Date asDateFromUTC(LocalDate localDate) {
        if (localDate == null) return null;
        return Date.from(localDate.atStartOfDay().atZone(DEFAULT_ZONE_OFFSET).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return Date.from(localDateTime.atZone(DEFAULT_ZONE_OFFSET).toInstant());
    }

    public static Date asDateFromUTC(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return Date.from(localDateTime.atZone(DEFAULT_ZONE_OFFSET).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        if (date == null) return null;
        return Instant.ofEpochMilli(date.getTime()).atZone(DEFAULT_ZONE_OFFSET).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        if (date == null) return null;
        return Instant.ofEpochMilli(date.getTime()).atZone(DEFAULT_ZONE_OFFSET).toLocalDateTime();
    }

    public static LocalTime asLocalTime(Date date) {
        if (date == null) return null;
        return asLocalDateTime(date).toLocalTime();
    }

    public static LocalDateTime getBeginCurrDay(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime
                .withHour(0)
                .withMinute(0)
                .withSecond(0);
    }

    public static LocalDateTime getBeginCurrDay(Date date) {
        if (date == null) return null;
        return getBeginCurrDay(asLocalDateTime(date));
    }

    public static Date getBeginCurrDayAsDate(Date date) {
        if (date == null) return null;
        return asDate(getBeginCurrDay(date));
    }

    public static LocalDateTime getEndCurrDay(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime
                .withHour(23)
                .withMinute(59)
                .withSecond(59);
    }

    public static LocalDateTime getEndCurrDay(Date date) {
        if (date == null) return null;
        return getEndCurrDay(asLocalDateTime(date));
    }

    public static Date getEndCurrDayAsDate(Date date) {
        if (date == null) return null;
        return asDate(getEndCurrDay(date));
    }

    public static LocalDateTime getBeginNextDay(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .plusDays(1);
    }

    public static LocalDateTime getBeginNextDay(Date date) {
        if (date == null) return null;
        return getBeginNextDay(asLocalDateTime(date));
    }

    public static LocalDateTime getBeginPreviousDay(Date date) {
        if (date == null) return null;
        return asLocalDateTime(date)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .minusDays(1);
    }

    public static Date getBeginPreviousDayAsDate(Date date) {
        if (date == null) return null;
        return asDate(getBeginPreviousDay(date));
    }

    public static Date getBeginNextDayAsDate(Date date) {
        if (date == null) return null;
        return asDate(getBeginNextDay(date));
    }

    public static LocalDateTime getBeginCurrMonth(Date date) {
        if (date == null) return null;
        return asLocalDateTime(date)
                .withDayOfMonth(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0);
    }

    public static Date getBeginCurrMonthAsDate(Date date) {
        if (date == null) return null;
        return asDate(getBeginCurrMonth(date));
    }

    public static LocalDateTime getBeginCurrYear(Date date) {
        if (date == null) return null;
        return asLocalDateTime(date)
                .withDayOfYear(1)
                .withMonth(1)
                .withDayOfMonth(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0);
    }

    public static Date getBeginCurrYearAsDate(Date date) {
        if (date == null) return null;
        return asDate(getBeginCurrYear(date));
    }

    public static Date parse(String dateStr, String format) {
        if (StringUtils.isEmpty(dateStr)) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(DEFAULT_TIME_ZONE);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * @param dateStr dateStr
     * @param format format
     * @return Date
     */
    public static LocalDateTime parseToLocalDateTime(String dateStr, String format) {
        if (StringUtils.isEmpty(dateStr)) return null;

        Date date = parse(dateStr, format);
        return asLocalDateTime(date);
    }

    /**
     * @param dateStr dateStr
     * @param format format
     * @return Date
     */
    public static LocalDate parseToLocalDate(String dateStr, String format) {
        if (StringUtils.isEmpty(dateStr)) return null;

        Date date = parse(dateStr, format);
        return asLocalDate(date);
    }

    /**
     * Does they equal
     *
     * @param first
     * @param second
     * @return
     */
    public static boolean equals(Date first, Date second) {
        if (first == null && second == null)
            return true;

        if (first == null || second == null)
            return false;

        return asLocalDateTime(first).equals(asLocalDateTime(second));

    }

    /**
     * Parse format ISO with/without time
     *
     * @param dateStr dateStr
     * @return Date
     */
    public static Date parseISO(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }

        if (dateStr.contains("T"))
            return parse(dateStr, DateFormatEnum.ISO.getFormat());

        return parse(dateStr, DateFormatEnum.ISO_WITHOUT_TIME.getFormat());
    }

    /**
     * Parse format ISO with/without time
     *
     * @param dateStr dateStr
     * @return java.sql.Date
     */
    public static java.sql.Date parseSqlISO(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        return convertUtilToSql(parseISO(dateStr));
    }

    /**
     * @param dateStr dateStr
     * @return LocalDateTime
     */
    public static LocalDateTime parseISOToLocalDateTime(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Date date = parseISO(dateStr);
        return asLocalDateTime(date);
    }

    /**
     * @param dateStr dateStr
     * @return LocalDate
     */
    public static LocalDate parseISOToLocalDate(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Date date = parseISO(dateStr);
        return asLocalDate(date);
    }

    /**
     * If dateFrom > dateTo return negative count of days
     * If dateFrom < dateTo return positive count of days
     * else == zero
     *
     * @param dateFrom dateFrom
     * @param dateTo   dateTo
     * @return Long
     */
    public static Long getCountDaysBetween(Date dateFrom, Date dateTo) {
        if (dateFrom == null || dateTo == null) {
            return 0L;
        }

        LocalDate localFrom = DateUtils.asLocalDate(dateFrom);
        LocalDate localTo = DateUtils.asLocalDate(dateTo);

        if (localFrom.isEqual(localTo)) {
            return 0L;
        }

        return ChronoUnit.DAYS.between(localFrom, localTo);
    }

    /**
     * @param date date
     * @param format format
     * @return date in formatted string
     */
    public static String toString(Date date, String format) {
        if (date == null) {
            return null;
        }

        requireNonNull(format, "Cannot be null date format");

        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * @param date date
     * @param format format
     * @return date in formatted string
     */
    public static String toString(LocalDateTime date, String format) {
        return toString(asDate(date), format);
    }

    /**
     * @param date date
     * @param format format
     * @return date in formatted string
     */
    public static String toString(LocalDate date, String format) {
        return toString(asDate(date), format);
    }

    /**
     * @param date date
     * @return string date in ISO format
     */
    public static String toStringISO(Date date) {
        if (date == null) return null;

        return toString(date, DateFormatEnum.ISO.getFormat());
    }

    /**
     * @param date date
     * @return string date in ISO format without time
     */
    public static String toStringISOWithoutTime(Date date) {
        if (date == null) return null;

        return toString(date, DateFormatEnum.ISO_WITHOUT_TIME.getFormat());
    }

    /**
     * @param localDateTime localDateTime
     * @return string date in ISO format
     */
    public static String toStringISO(LocalDateTime localDateTime) {
        return toStringISO(asDate(localDateTime));
    }

    /**
     * @param localDate localDate
     * @return string date in ISO format
     */
    public static String toStringISO(LocalDate localDate) {
        return toStringISO(asDate(localDate));
    }

    /**
     * @param obj obj
     * @return string date formatted to ISO
     */
    public static String toStringISOIfDate(Object obj) {
        if (obj == null) return null;

        if (obj instanceof Date) {
            return toStringISO((Date) obj);
        } else if (obj instanceof LocalDateTime) {
            return toStringISO((LocalDateTime) obj);
        } else if (obj instanceof LocalDate) {
            return toStringISO((LocalDate) obj);
        }

        return obj.toString();
    }

    /**
     * @param date      date
     * @param countDays countDays may be positive or negative
     * @return Date
     */
    public static Date calcIncOrDecDays(Date date, long countDays) {
        if (date == null) return null;
        LocalDateTime localDateTime = asLocalDateTime(date);
        if (countDays < 0)
            return asDate(localDateTime.minusDays(Math.abs(countDays)));
        if (countDays > 0)
            return asDate(localDateTime.plusDays(Math.abs(countDays)));

        return date;
    }


    /**
     * Create interval by dates
     *
     * @param start  - start date. It can be null
     * @param finish - finish date. It can be null
     * @return interval
     */
    public static Interval createIntervalByDates(Date start, Date finish) {
        long startTimestamp = UNKNOWN_START_DATE;
        long endTimestamp = UNKNOWN_FINISH_DATE;

        if (start != null)
            startTimestamp = start.getTime();

        if (finish != null)
            endTimestamp = finish.getTime();

        return new Interval(startTimestamp, endTimestamp);
    }

    /**
     * Get all dates in interval
     *
     * @param startDate - first
     * @param endDate   - last
     * @return all dates in interval
     */
    public static List<LocalDate> getAllDatesInInterval(LocalDate startDate, LocalDate endDate) {
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
    }
}