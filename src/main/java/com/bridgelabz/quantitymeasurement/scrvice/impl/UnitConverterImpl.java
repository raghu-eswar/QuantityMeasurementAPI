package com.bridgelabz.quantitymeasurement.scrvice.impl;

import com.bridgelabz.quantitymeasurement.enumeration.Units;
import com.bridgelabz.quantitymeasurement.model.Quantity;
import com.bridgelabz.quantitymeasurement.scrvice.UnitConverter;
import org.springframework.stereotype.Service;

@Service
public class UnitConverterImpl implements UnitConverter {

    @Override
    public Quantity convert(Quantity quantity, Units conversionUnit) {
        if (conversionUnit.type.equals(quantity.getUnit().type)) {
            double v = (this.getUnitValue(quantity) - conversionUnit.addend) /
                    conversionUnit.multiplicand;
            quantity.setValue(v);
            quantity.setUnit(conversionUnit);
            return quantity;
        }
        return null;
    }

    private double getUnitValue(Quantity quantity)  {
        return (quantity.getUnit().multiplicand * quantity.getValue()) + quantity.getUnit().addend;
    }

}
