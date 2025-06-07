package com.management.store.controller;

import com.management.store.exceptions.NegativeNumericValueException;
import com.management.store.exceptions.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.MessageFormat;

@RestControllerAdvice
class GlobalContrllerAdvice {

    private static final String PRODUCT_NOT_FOUND = "Product identified by id {0} was not found";
    private static final String NEGATIVE_NO = "Price or Quantity cannot have negative value";
    private static final Logger log = LoggerFactory.getLogger(GlobalContrllerAdvice.class);

//    @ExceptionHandler(ProductNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    String productNotFoundHandler(ProductNotFoundException ex) {
//        String message = MessageFormat.format(PRODUCT_NOT_FOUND, ex.getMessage());
//        log.error(message, ex);
//        return message;
//    }
//
//    @ExceptionHandler(NegativeNumericValueException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    String negativeValueFoundHandler(NegativeNumericValueException ex) {
//        log.error(NEGATIVE_NO, ex);
//        return NEGATIVE_NO;
//    }

    //Pattern Matching Switch
    @ExceptionHandler(Exception.class)
    ResponseEntity<String> errorHandler(Exception ex) {
        String message = "";
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        switch (ex) {
            case ProductNotFoundException e1 -> {
                message = MessageFormat.format(PRODUCT_NOT_FOUND, e1.getParam());
                httpStatus = HttpStatus.NOT_FOUND;
                log.error(message, ex);
            }
            case NegativeNumericValueException e2 -> {
                message = NEGATIVE_NO;
                httpStatus = HttpStatus.BAD_REQUEST;
                log.error(NEGATIVE_NO, ex);
            }
            default -> throw new IllegalStateException("Unexpected value: " + ex);
        }
        return new ResponseEntity<>(message, httpStatus);
    }
}