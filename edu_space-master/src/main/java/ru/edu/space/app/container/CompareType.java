package ru.edu.space.app.container;

/**
 * These types of comparisons are used for GET parameters
 * Ex.: /url?count[lt]=20
 */
public enum CompareType {

    EQUAL("eq"),
    LESS_THAN("lt"),
    LESS_THAN_OR_EQUAL("lte"),
    GREATER_THAN("gt"),
    GREATER_THAN_OR_EQUAL("gte");

    private String value;

    CompareType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CompareType fromString(String text) {
        for (CompareType type : CompareType.values()) {
            if (type.value.equalsIgnoreCase(text.trim())) {
                return type;
            }
        }
        return null;
    }
}
