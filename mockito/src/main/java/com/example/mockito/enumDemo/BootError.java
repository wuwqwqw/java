package com.example.mockito.enumDemo;

public enum BootError {

    FEIGN_CALL_FAILED(10000000, "Feign call failed"),
    COMMON_ERROR(10000001, "Common error occurs"),
    BAD_REQUEST(10000002, "Bad request"),
    INTERNAL_SERVER_ERROR(10000003, "Internal Server Error"),
    INTENT_NOT_FOUNT(10000000 + 11, "intent not found");

    private final int code;

    private final String message;

    BootError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BootError valueOf(int status) {
        switch (status) {
            case 400:
            case 401:
            case 403:
                return BAD_REQUEST;
            case 500:
            case 502:
            case 503:
            case 504:
            default:
                return INTERNAL_SERVER_ERROR;
        }
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

}