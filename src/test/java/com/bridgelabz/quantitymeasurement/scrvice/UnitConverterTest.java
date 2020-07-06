package com.bridgelabz.quantitymeasurement.scrvice;

import com.bridgelabz.quantitymeasurement.model.Quantity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.bridgelabz.quantitymeasurement.enumeration.Units.CENTIMETER;
import static com.bridgelabz.quantitymeasurement.enumeration.Units.MILLIMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UnitConverterTest {

    @Autowired
    UnitConverter unitConverter;

    @Test
    void givenUnitsAndValues_convertUnits_shouldReturnConvertedQuantity() {
        Quantity quantity = unitConverter.convert(new Quantity(10, MILLIMETER), CENTIMETER);
        assertEquals(quantity.getValue(), 1);
        assertEquals(quantity.getUnit(), CENTIMETER);
    }
}