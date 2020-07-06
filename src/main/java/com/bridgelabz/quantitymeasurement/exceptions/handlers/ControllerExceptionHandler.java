package com.bridgelabz.quantitymeasurement.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleConflict(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(ex.getCause().getCause().getMessage(), HttpStatus.BAD_REQUEST);
    }
}