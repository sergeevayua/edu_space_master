package ru.edu.space.app.container;

import java.util.List;
import java.util.Map;

public class FieldsAndQueryParamsBuilder {
    private Map<String, List<?>> fields;
    private QueryParams queryParams;

    public FieldsAndQueryParamsBuilder setFields(Map<String, List<?>> fields) {
        this.fields = fields;
        return this;
    }

    public FieldsAndQueryParamsBuilder setQueryParams(QueryParams queryParams) {
        this.queryParams = queryParams;
        return this;
    }

    public FieldsAndQueryParams createFieldsAndQueryParams() {
        return new FieldsAndQueryParams(fields, queryParams);
    }
}