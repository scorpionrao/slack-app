package com.test.slack.common.model;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = { Throwable.class })
    protected ResponseEntity<String> handleAnyException(Throwable exception) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Content-Type", Arrays.asList(MediaType.APPLICATION_JSON_VALUE));
        return new ResponseEntity<>(exception.getMessage(), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
