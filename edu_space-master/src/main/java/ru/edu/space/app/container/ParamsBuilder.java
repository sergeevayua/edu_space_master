package ru.edu.space.app.container;

import java.util.List;
import java.util.Map;

public class ParamsBuilder {
    protected Map<String, List<?>> props;
    protected QueryParams queryParams;
    protected List<String> fetchingFields;

    public ParamsBuilder setProps(Map<String, List<?>> props) {
        this.props = props;
        return this;
    }

    public ParamsBuilder setQueryParams(QueryParams queryParams) {
        this.queryParams = queryParams;
        return this;
    }

    public ParamsBuilder setFetchingFields(List<String> fetchingFields) {
        this.fetchingFields = fetchingFields;
        return this;
    }

    public Params createParams() {
        return new Params(props, queryParams, fetchingFields);
    }
}