package com.bridgelabz.quantitymeasurement.exceptions.handlers;

import com.bridgelabz.quantitymeasurement.exceptions.UnitConversionFailedException;
import com.bridgelabz.quantitymeasurement.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Response> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return ResponseEntity.badRequest().body(new Response(exception.getCause().getCause().getMessage(),HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(UnitConversionFailedException.class)
    public ResponseEntity<Response> handleUnitConversionFailedException(UnitConversionFailedException exception) {
        return ResponseEntity.status(exception.httpStatus).body(new Response(exception.getMessage(), HttpStatus.BAD_REQUEST));
    }

}