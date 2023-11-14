package com.an.pp3.constant;

public enum Gender {
    MALE("мужской"),
    FEMALE("женский"),
    UNKNOWN("не указан");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
