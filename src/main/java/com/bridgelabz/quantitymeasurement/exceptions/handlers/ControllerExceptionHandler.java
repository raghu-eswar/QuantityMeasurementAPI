package com.bridgelabz.quantitymeasurement.exceptions.handlers;

import com.bridgelabz.quantitymeasurement.exceptions.UnitConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return new ResponseEntity<>(exception.getCause().getCause().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnitConversionFailedException.class)
    public ResponseEntity<String> handleUnitConversionFailedException(UnitConversionFailedException exception) {
        return new ResponseEntity<>(exception.getMessage(),exception.httpStatus);
    }
}