package com.zerobase.gurumesi.restaurant.type;

public enum sortEnum {
    NAME("NAME"),
    STAR("STAR"),
    DISTANCE("DISTANCE");

    private final String enumName;

    sortEnum(String name) {
        enumName = name;
    }


}
