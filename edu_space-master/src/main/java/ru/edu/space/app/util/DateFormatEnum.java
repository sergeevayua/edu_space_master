package ru.edu.space.app.util;


public enum DateFormatEnum {
    ISO(Constants.ISO),
    ISO_WITHOUT_TIME(Constants.ISO_WITHOUT_TIME);

    public static class Constants {
        public static final String ISO = "yyyy-MM-dd'T'HH:mm:ss";       // ISO 8601
        public static final String ISO_WITHOUT_TIME = "yyyy-MM-dd";
    }

    private String format;

    DateFormatEnum(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
