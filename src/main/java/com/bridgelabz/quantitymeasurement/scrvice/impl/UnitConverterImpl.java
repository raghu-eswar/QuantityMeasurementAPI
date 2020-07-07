package com.bridgelabz.quantitymeasurement.scrvice.impl;

import com.bridgelabz.quantitymeasurement.enumeration.Units;
import com.bridgelabz.quantitymeasurement.exceptions.UnitConversionFailedException;
import com.bridgelabz.quantitymeasurement.model.Quantity;
import com.bridgelabz.quantitymeasurement.scrvice.UnitConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UnitConverterImpl implements UnitConverter {

    @Override
    public Quantity convert(Quantity quantity, Units conversionUnit) {
        if (!conversionUnit.type.equals(quantity.getUnit().type)) {
            throw new UnitConversionFailedException("can not convert " + quantity.getUnit() + " to " + conversionUnit, HttpStatus.BAD_REQUEST);
        }
        double convertedValue = (quantity.getBaseValue() - conversionUnit.addend)/conversionUnit.multiplicand;
        quantity.setValue(convertedValue);
        quantity.setUnit(conversionUnit);
        return quantity;
    }

    @Override
    public String[] getAllUnitTypes() {
        return Arrays.stream(Units.values()).map(units -> units.type).distinct().toArray(String[]::new);
    }

}
