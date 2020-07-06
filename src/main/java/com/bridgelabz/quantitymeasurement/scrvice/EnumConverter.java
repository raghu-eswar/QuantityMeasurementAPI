package com.bridgelabz.quantitymeasurement.scrvice;

import com.bridgelabz.quantitymeasurement.enumeration.Units;
import com.bridgelabz.quantitymeasurement.exceptions.EnumConversionFailedException;
import org.springframework.core.convert.converter.Converter;

public class EnumConverter implements Converter<String, Units> {
    @Override
    public Units convert(String unit) {
        try {
            return Units.valueOf(unit.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new EnumConversionFailedException(unit+" is not a proper unit");
        }
    }
}