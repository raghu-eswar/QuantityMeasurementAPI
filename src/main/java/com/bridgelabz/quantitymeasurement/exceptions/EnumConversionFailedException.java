package com.bridgelabz.quantitymeasurement.exceptions;

import com.bridgelabz.quantitymeasurement.enumeration.ExceptionMessages;

public class EnumConversionFailedException extends RuntimeException{

    public EnumConversionFailedException(ExceptionMessages message) {
        super(String.valueOf(message));
    }
}