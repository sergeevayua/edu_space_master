package ru.edu.space.app.container;

public class QueryParamsBuilder {
    private String sortBy;
    private OrderType orderType;
    private Integer limit;
    private Integer start;
    private IntervalParams intervalParams;

    public QueryParamsBuilder setSortBy(String sortBy) {
        this.sortBy = sortBy;
        return this;
    }

    public QueryParamsBuilder setOrderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public QueryParamsBuilder setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public QueryParamsBuilder setStart(Integer start) {
        this.start = start;
        return this;
    }

    public QueryParamsBuilder setIntervalParams(IntervalParams intervalParams) {
        this.intervalParams = intervalParams;
        return this;
    }

    public QueryParams createQueryParams() {
        return new QueryParams(sortBy, orderType, limit, start, intervalParams);
    }
}