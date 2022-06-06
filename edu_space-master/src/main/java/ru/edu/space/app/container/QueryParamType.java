package ru.edu.space.app.container;

/**
 * Using delimiter "_" for separate from real field name, because cannot exist field in Entity with symbol "_"
 * Add here the specific parameters that should be processed from the GET parameters
 *
 */
public enum QueryParamType {

    SORT_PARAM("sort_by"),
    ORDER_PARAM("order_by"),
    LIMIT_PARAM("limit_by"),
    START_PARAM("start_by"),

    INTERVAL_FIELD("interval_field"),
    INTERVAL_BASE("interval_base"),
    INTERVAL_LEFT("interval_left"),
    INTERVAL_RIGHT("interval_right");

    private String value;

    QueryParamType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static QueryParamType fromString(String text) {
        for (QueryParamType type : QueryParamType.values()) {
            if (type.value.equalsIgnoreCase(text.trim())) {
                return type;
            }
        }
        return null;
    }
}
