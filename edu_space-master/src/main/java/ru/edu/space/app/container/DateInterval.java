package ru.edu.space.app.container;

import java.util.Date;
import java.util.Objects;

import static ru.edu.space.app.util.DateUtils.asLocalDateTime;


public final class DateInterval {

    private final Date startDate;
    private final Date finishDate;

    private DateInterval(Date startDate, Date finishDate) {
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public static DateInterval of(Date startDate, Date finishDate) {
        return new DateInterval(startDate, finishDate);
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateInterval that = (DateInterval) o;
        return Objects.equals(asLocalDateTime(startDate), asLocalDateTime(that.startDate)) &&
                Objects.equals(asLocalDateTime(finishDate), asLocalDateTime(that.finishDate));
    }

    @Override
    public int hashCode() {
        return Objects.hash(asLocalDateTime(startDate), asLocalDateTime(finishDate));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DateInterval{");
        sb.append("startDate=").append(startDate);
        sb.append(", finishDate=").append(finishDate);
        sb.append('}');
        return sb.toString();
    }
}
