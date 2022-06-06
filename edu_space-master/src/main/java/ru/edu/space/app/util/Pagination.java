package ru.edu.space.app.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static java.util.Collections.emptyList;

/**
 * use this class for pagination for any entities
 *
 */
public final class Pagination<T> {

    private final Collection<T> data;
    private final long total;

    private Pagination(Collection<T> data, long total) {
        this.data = Objects.requireNonNull(data);
        this.total = total;
    }

    public static <T> Pagination<T> getEmpty() {
        return of(emptyList(), 0L);
    }

    public static <T> Pagination<T> of(Collection<T> data, long total) {
        return new Pagination<>(data, total);
    }

    public Collection<T> getData() {
        return new ArrayList<>(data);
    }

    public long getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Pagination<?> that = (Pagination<?>) object;
        return total == that.total &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, total);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pagination{");
        sb.append("data=").append(data);
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }
}
