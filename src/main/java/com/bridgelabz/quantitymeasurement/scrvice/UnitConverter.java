package com.bridgelabz.quantitymeasurement.scrvice;

import com.bridgelabz.quantitymeasurement.enumeration.UnitTypes;
import com.bridgelabz.quantitymeasurement.enumeration.Units;
import com.bridgelabz.quantitymeasurement.model.Quantity;

public interface UnitConverter {
    Quantity convert(Quantity quantity, Units conversionUnit);

    UnitTypes[] getAllUnitTypes();

    Units[] getValidUnitsOf(UnitTypes unitType);
}
