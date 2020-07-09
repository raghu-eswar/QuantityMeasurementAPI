package com.bridgelabz.quantitymeasurement.exceptions;

import com.bridgelabz.quantitymeasurement.enumeration.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class UnitConversionFailedException extends RuntimeException {

    public HttpStatus httpStatus;
    public UnitConversionFailedException(ExceptionMessages message, HttpStatus httpStatus) {
        super(String.valueOf(message));
        this.httpStatus = httpStatus;
    }
}
