package ru.edu.space.app.container;

import java.util.Objects;

/**
 * this is a container containing parameters from GET requests
 */
public final class QueryParams {
    private final String sortBy;
    private final OrderType orderType;
    private final Integer limit;
    private final Integer start;
    private final IntervalParams intervalParams;

    public static QueryParamsBuilder builder() {
        return new QueryParamsBuilder();
    }

    QueryParams(String sortBy, OrderType orderType, Integer limit, Integer start, IntervalParams intervalParams) {
        this.sortBy = sortBy;
        this.orderType = orderType;
        this.limit = limit;
        this.start = start;
        this.intervalParams = intervalParams;
    }

    public String getSortBy() {
        return sortBy;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getStart() {
        return start;
    }

    public IntervalParams getIntervalParams() {
        return intervalParams;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        QueryParams that = (QueryParams) object;
        return Objects.equals(sortBy, that.sortBy) &&
                orderType == that.orderType &&
                Objects.equals(limit, that.limit) &&
                Objects.equals(start, that.start) &&
                Objects.equals(intervalParams, that.intervalParams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortBy, orderType, limit, start, intervalParams);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QueryParams{");
        sb.append("sortBy='").append(sortBy).append('\'');
        sb.append(", orderType=").append(orderType);
        sb.append(", limit=").append(limit);
        sb.append(", start=").append(start);
        sb.append(", intervalParams=").append(intervalParams);
        sb.append('}');
        return sb.toString();
    }
}
