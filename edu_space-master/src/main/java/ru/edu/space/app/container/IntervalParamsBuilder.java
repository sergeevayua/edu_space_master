package ru.edu.space.app.container;

public class IntervalParamsBuilder {
    private String fieldName;
    private Object baseValue;
    private Integer left;
    private Integer right;

    public IntervalParamsBuilder setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public IntervalParamsBuilder setBaseValue(Object baseValue) {
        this.baseValue = baseValue;
        return this;
    }

    public IntervalParamsBuilder setLeft(Integer left) {
        this.left = left;
        return this;
    }

    public IntervalParamsBuilder setRight(Integer right) {
        this.right = right;
        return this;
    }

    public IntervalParams createIntervalParams() {
        return new IntervalParams(fieldName, baseValue, left, right);
    }
}