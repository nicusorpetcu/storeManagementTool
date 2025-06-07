package com.management.store.exceptions;

public class ProductNotFoundException extends Exception {

    private String param;
    private String message;

    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String param) {
        this.param = param;
    }

    public ProductNotFoundException(String param, String message) {
        this.param = param;
        this.message = message;
    }

    public String getParam() {
        return param;
    }

    public String getMessage() {
        return message;
    }
}
