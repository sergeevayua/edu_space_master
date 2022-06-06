package ru.edu.space.app.util;

/**
 * Builder to quickly create JSON
 *
 *
 */
public final class JsonBuilder {

    private final MapBuilder<String, Object> mapBuilder = new MapBuilder<>();

    public static String createByOneParam(String key, Object object) {
        return new JsonBuilder()
                .put(key, object)
                .toString();
    }

    public static JsonBuilder jsonBuilder() {
        return new JsonBuilder();
    }

    public JsonBuilder put(String key, Object object) {
        mapBuilder.put(key, object);
        return this;
    }

    public JsonBuilder putNullValue(String key) {
        mapBuilder.put(key, null);
        return this;
    }

    public String build() {
        return JacksonUtil.toString(mapBuilder.build());
    }

    @Override
    public String toString() {
        return build();
    }
}
