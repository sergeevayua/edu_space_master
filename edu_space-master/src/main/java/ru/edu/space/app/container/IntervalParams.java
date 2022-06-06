package ru.edu.space.app.container;

import java.util.Objects;

public final class IntervalParams {
    private final String fieldName;
    private final Object baseValue;
    private final Integer left;
    private final Integer right;

    public static IntervalParamsBuilder builder() {
        return new IntervalParamsBuilder();
    }

    IntervalParams(String fieldName, Object baseValue, Integer left, Integer right) {
        this.fieldName = fieldName;
        this.baseValue = baseValue;
        this.left = left;
        this.right = right;
    }

    public boolean anyFieldIsNull() {
        return getFieldName() == null || getBaseValue() == null || getLeft() == null || getRight() == null;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getBaseValue() {
        return baseValue;
    }

    public Integer getLeft() {
        return left;
    }

    public Integer getRight() {
        return right;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        IntervalParams that = (IntervalParams) object;
        return Objects.equals(fieldName, that.fieldName) &&
                Objects.equals(baseValue, that.baseValue) &&
                Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName, baseValue, left, right);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IntervalParams{");
        sb.append("fieldName='").append(fieldName).append('\'');
        sb.append(", baseValue=").append(baseValue);
        sb.append(", left=").append(left);
        sb.append(", right=").append(right);
        sb.append('}');
        return sb.toString();
    }
}
