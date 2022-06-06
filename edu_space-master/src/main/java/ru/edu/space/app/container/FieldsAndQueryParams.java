package ru.edu.space.app.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.springframework.util.ObjectUtils.isEmpty;

public final class FieldsAndQueryParams {

    private final Map<String, List<?>> fields;
    private final QueryParams queryParams;

    public static FieldsAndQueryParamsBuilder builder() {
        return new FieldsAndQueryParamsBuilder();
    }

    FieldsAndQueryParams(Map<String, List<?>> fields, final QueryParams queryParams) {
        this.fields = fields;
        this.queryParams = queryParams;
    }

    public static FieldsAndQueryParams of(Map<String, List<?>> fields, final QueryParams queryParams) {
        return builder()
                .setFields(fields)
                .setQueryParams(queryParams)
                .createFieldsAndQueryParams();
    }

    public Map<String, List<?>> getFields() {
        if (isEmpty(fields)) {
            return new HashMap<>();     // do not use immutable collections because further adding new values is used.
        }                               // It is necessary to return constantly new collections.
        return new HashMap<>(fields);
    }

    public QueryParams getQueryParams() {
        return queryParams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldsAndQueryParams that = (FieldsAndQueryParams) o;
        return Objects.equals(fields, that.fields) &&
                Objects.equals(queryParams, that.queryParams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fields, queryParams);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FieldsAndQueryParams{");
        sb.append("fields=").append(fields);
        sb.append(", queryParams=").append(queryParams);
        sb.append('}');
        return sb.toString();
    }
}
