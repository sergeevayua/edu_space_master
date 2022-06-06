package ru.edu.space.app.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

public final class JacksonUtil {

    private JacksonUtil() {}

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Detect is passed json string is array?
     * Ex.: '[{"name": 1}]' is true
     *
     * @param json array string
     * @return true or false
     */
    public static boolean isJsonArray(String json) {
        return json.trim().charAt(0) == '[';
    }

    /**
     * @param json array string
     * @throws IllegalArgumentException is passed string json is not array
     */
    public static void validateJsonIsArray(String json) {
        if (isJsonArray(json)) {
            return;
        }

        throw new IllegalArgumentException("The transmitted json must be an array");
    }

    /**
     * Convert json string to object by clazz
     *
     * @param string string
     * @param clazz clazz
     * @param <T> T
     * @return object
     */
    public static <T> T fromString(String string, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(string, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("The given string value: "
                    + string + " cannot be transformed to Json object by class: "
                    + clazz.getName(), e);
        }
    }

    /**
     * Convert json string contains array to list of object by clazz
     *
     * @param stringArray stringArray
     * @param clazz clazz
     * @param <T> T
     * @return list of object
     */
    public static <T> List<T> fromStringArray(String stringArray, Class<T> clazz) {
        validateJsonIsArray(stringArray);

        try {
            CollectionType javaType = OBJECT_MAPPER.getTypeFactory()
                    .constructCollectionType(List.class, clazz);
            return OBJECT_MAPPER.readValue(stringArray, javaType);
        } catch (IOException e) {
            throw new IllegalArgumentException("The given stringArray value: "
                    + stringArray + " cannot be transformed to Json object by class: "
                    + clazz.getName(), e);
        }
    }

    /**
     * Danger! Passed param 'objectForUpdate' will be change
     * @param json json
     * @param objectForUpdate objectForUpdate
     */
    public static void updateFromString(String json, Object objectForUpdate) {
        try {
            OBJECT_MAPPER.readerForUpdating(objectForUpdate).readValue(json);
        } catch (IOException e) {
            throw new IllegalArgumentException("The given json value: "
                    + json + " cannot be transformed for object update: "
                    + objectForUpdate, e);
        }
    }


    /**
     * remove id from json
     */
    public static String removeId(String json) {
        try {
            Map<Object, Object> map = OBJECT_MAPPER.readValue(json, Map.class);
            map.remove("id");
            return OBJECT_MAPPER.writeValueAsString(map);
        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }
    }



    /**
     * Return updated object from json
     * @param json json
     * @param object object
     * @return updated object
     */
    public static <T> T updateFromStringByClone(String json, final T object) {
        T cloneEntity = clone(object);
        try {
            OBJECT_MAPPER.readerForUpdating(cloneEntity).readValue(json);
            return cloneEntity;
        } catch (IOException e) {
            throw new IllegalArgumentException("The given json value: "
                    + json + " cannot be transformed for object update: "
                    + cloneEntity, e);
        }
    }

    /**
     * @param value object
     * @return json string
     */
    public static String toString(Object value) {
        if (isNull(value)) {
            return "{}";
        }

        try {
            return OBJECT_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("The given Json object value: "
                    + value + " cannot be transformed to a String", e);
        }
    }

    /**
     * Convert to json by passed JsonView class
     *
     * @param value object
     * @param JsonView jsonView
     * @return json
     */
    public static String toStringWithJsonView(Object value, Class<?> JsonView) {
        if (isNull(value)) {
            return "{}";
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper
                    .writerWithView(JsonView)
                    .writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("The given Json object value: "
                    + value + " cannot be transformed to a String", e);
        }
    }

    /**
     * @param value object
     * @return json string without null fields
     */
    public static String toStringWithoutNullFields(Object value) {
        if (isNull(value)) {
            return "{}";
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("The given Json object value: "
                    + value + " cannot be transformed to a String", e);
        }
    }

    public static JsonNode toJsonNode(String value) {
        try {
            return OBJECT_MAPPER.readTree(value);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Deep clone
     *
     * @param value value
     * @param <T> T
     * @return cloned object
     */
    @SuppressWarnings("unchecked")
    public static <T> T clone(T value) {
        return fromString(toString(value), (Class<T>) value.getClass());
    }

    /**
     * @param jsonStr jsonStr
     * @return beauty json
     */
    public static String prettyJson(String jsonStr) {
        if (isNull(jsonStr)) {
            return "{}";
        }

        try {
            Object json = OBJECT_MAPPER.readValue(jsonStr, Object.class);
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot beatify passed json string: " + jsonStr, e);
        }
    }
}