package ru.edu.space.app.container;

import java.util.Objects;

public final class FieldCompareType {

    public static final String BEGIN_DELIMITER = "[";
    public static final String END_DELIMITER = "]";

    private final String fieldWithCompareType;

    public static boolean isFieldWithCompareType(String fieldName) {
        return fieldName.contains(BEGIN_DELIMITER) && fieldName.contains(END_DELIMITER);
    }

    private FieldCompareType(String fieldWithCompareType) {
        this.fieldWithCompareType = fieldWithCompareType;
    }

    public static FieldCompareType of(String field) {
        return new FieldCompareType(field);
    }

    public static String createFieldNameWithCompareType(String fieldName, CompareType compareType) {
        StringBuilder builder = new StringBuilder();
        builder.append(fieldName);
        builder.append(BEGIN_DELIMITER);
        builder.append(compareType.getValue());
        builder.append(END_DELIMITER);
        return builder.toString();
    }

    public String getFieldName() {
        int indexPos = fieldWithCompareType.indexOf(BEGIN_DELIMITER);
        if (indexPos != -1) {
           return fieldWithCompareType.substring(0, indexPos);
        }
        return fieldWithCompareType;
    }

    public CompareType getCompareType() {
        int indexPosBegin = fieldWithCompareType.indexOf(BEGIN_DELIMITER);
        int indexPosEnd = fieldWithCompareType.indexOf(END_DELIMITER);
        if (indexPosBegin == -1 || indexPosEnd == -1) {
            return null;
        }

        String strCompareType = fieldWithCompareType.substring(indexPosBegin + 1, indexPosEnd);
        return CompareType.fromString(strCompareType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldCompareType that = (FieldCompareType) o;
        return Objects.equals(fieldWithCompareType, that.fieldWithCompareType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldWithCompareType);
    }
}
