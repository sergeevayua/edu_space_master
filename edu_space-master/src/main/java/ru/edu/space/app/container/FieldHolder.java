package ru.edu.space.app.container;

import java.util.Objects;

public class FieldHolder {

    private String name;

    private Object value;

    private boolean isNotNullField;

    /**
     * Stores the name of the dependent object field name.
     * Ex.: "private Well well" => "well"
     *
     * If relationFieldName null - field without relation
     * if relationFieldName not null - name of relation object.
     */
    private String relationFieldName;

    public FieldHolder() {}

    public FieldHolder(String name, Object value, boolean isNotNullField) {
        this.name = name;
        this.value = value;
        this.isNotNullField = isNotNullField;
    }

    public FieldHolder(String name, Object value, boolean isNotNullField, String relationFieldName) {
        this(name, value, isNotNullField);
        this.relationFieldName = relationFieldName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getRelationFieldName() {
        return relationFieldName;
    }

    public void setRelationFieldName(String relationFieldName) {
        this.relationFieldName = relationFieldName;
    }

    public Boolean getNotNullField() {
        return isNotNullField;
    }

    public void setNotNullField(Boolean notNullField) {
        isNotNullField = notNullField;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        FieldHolder that = (FieldHolder) object;
        return isNotNullField == that.isNotNullField &&
                Objects.equals(name, that.name) &&
                Objects.equals(value, that.value) &&
                Objects.equals(relationFieldName, that.relationFieldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, isNotNullField, relationFieldName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FieldHolder{");
        sb.append("name='").append(name).append('\'');
        sb.append(", value=").append(value);
        sb.append(", isNotNullField=").append(isNotNullField);
        sb.append(", relationFieldName='").append(relationFieldName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
