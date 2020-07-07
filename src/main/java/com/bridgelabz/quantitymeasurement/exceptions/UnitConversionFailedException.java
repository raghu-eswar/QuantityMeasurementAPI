package com.bridgelabz.quantitymeasurement.exceptions;

import org.springframework.http.HttpStatus;

public class UnitConversionFailedException extends RuntimeException {

    public HttpStatus httpStatus;
    public UnitConversionFailedException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
