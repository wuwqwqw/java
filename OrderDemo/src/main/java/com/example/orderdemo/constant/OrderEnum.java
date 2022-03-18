package com.example.orderdemo.constant;

public enum OrderEnum {

    BLACK_PERSON(1),
    YELLOW_PERSON(0);

    private final int order;

    OrderEnum(int order) {
        this.order = order;
    }

    public int order() {
        return order;
    }
}
