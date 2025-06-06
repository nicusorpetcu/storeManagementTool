package com.management.store.exceptions;

public class ProductNotFoundException extends Exception{

    private String param;

    public ProductNotFoundException(){}
    public ProductNotFoundException(String param) {this.param = param;}

    public String getParam() {return param;}
}
