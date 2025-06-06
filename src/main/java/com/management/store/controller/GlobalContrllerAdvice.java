package com.management.store.controller;

import com.management.store.db.DatabaseLoader;
import com.management.store.exceptions.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalContrllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(GlobalContrllerAdvice.class);
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productNotFoundHandler(ProductNotFoundException ex) {
        log.error("Product not found",ex.getMessage(), ex);
        return ex.getMessage();
    }
}