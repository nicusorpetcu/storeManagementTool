package com.management.store.exceptions;

public class NegativeNumericValueException extends Exception{
    private String param;

    public NegativeNumericValueException(){}
    public NegativeNumericValueException(String param) {this.param = param;}

    public String getParam() {return param;}
}
