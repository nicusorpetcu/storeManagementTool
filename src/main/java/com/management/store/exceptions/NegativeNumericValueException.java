package com.management.store.exceptions;

public class NegativeNumericValueException extends Exception {
    private String param;
    private String message;

    public NegativeNumericValueException() {
    }

    public NegativeNumericValueException(String param) {
        this.param = param;
    }

    public NegativeNumericValueException(String param, String message) {}

    public String getParam() {
        return param;
    }

    public String getMessage() {return message;}
}
