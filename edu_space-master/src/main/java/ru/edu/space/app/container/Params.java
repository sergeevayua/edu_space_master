package ru.edu.space.app.container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.ObjectUtils.isEmpty;

public class Params {

    protected final Map<String, List<?>> props;
    protected final QueryParams queryParams;
    protected final List<String> fetchingFields;

    public static ParamsBuilder builder() {
        return new ParamsBuilder();
    }

    Params(Map<String, List<?>> props, QueryParams queryParams, List<String> fetchingFields) {
        this.props = props;
        this.queryParams = queryParams;
        this.fetchingFields = fetchingFields;
    }

    public Map<String, List<?>> getProps() {
        if (isEmpty(props)) {
            return new HashMap<>();      // do not use immutable collections because further adding new values is used.
        }                                // It is necessary to return constantly new collections.
        return new HashMap<>(props);
    }

    public QueryParams getQueryParams() {
        return queryParams;
    }

    public List<String> getFetchingFields() {
        if (isEmpty(fetchingFields)) {
            return new ArrayList<>();   // do not use immutable collections because further adding new values is used.
        }                               // It is necessary to return constantly new collections.
        return new ArrayList<>(fetchingFields);
    }
}
