package com.gpay.gpay.common;

import java.util.Arrays;

public enum ResponseStatus {
    S("Success"),
    F("Failed");
    private String name;

    ResponseStatus(String name) {
        this.name = name;
    }

    public ResponseStatus findEnum(String name) {
        return (ResponseStatus) Arrays.stream(values()).filter((data) -> {
            return data.name.equalsIgnoreCase(name);
        }).findFirst().orElse(null);
    }
}
