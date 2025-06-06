package com.management.store.controller;

import com.management.store.db.DatabaseLoader;
import com.management.store.exceptions.NegativeNumericValueException;
import com.management.store.exceptions.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.MessageFormat;

@RestControllerAdvice
class GlobalContrllerAdvice {

    private static final String PRODUCT_NOT_FOUND = "Product identified by id {0} was not found";
    private static final Logger log = LoggerFactory.getLogger(GlobalContrllerAdvice.class);
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productNotFoundHandler(ProductNotFoundException ex) {
        log.error(MessageFormat.format(PRODUCT_NOT_FOUND,ex.getParam()),ex.getMessage(), ex);
        return ex.getMessage();
    }

    @ExceptionHandler(NegativeNumericValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String negativeValueFoundHandler(NegativeNumericValueException ex) {
        log.error("Price or Quantity cannot have negative value",ex.getMessage(), ex);
        return ex.getMessage();
    }
}