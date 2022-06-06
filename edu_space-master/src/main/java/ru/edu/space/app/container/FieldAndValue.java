package ru.edu.space.app.container;

public final class FieldAndValue {

    private final String name;
    private final Object value;

    private FieldAndValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public static FieldAndValue of(String name, Object value) {
        return new FieldAndValue(name, value);
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
