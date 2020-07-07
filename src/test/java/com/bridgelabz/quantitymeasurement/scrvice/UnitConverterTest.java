package com.bridgelabz.quantitymeasurement.scrvice;

import com.bridgelabz.quantitymeasurement.exceptions.UnitConversionFailedException;
import com.bridgelabz.quantitymeasurement.model.Quantity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.bridgelabz.quantitymeasurement.enumeration.Units.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UnitConverterTest {

    @Autowired
    UnitConverter unitConverter;

    @Test
    void givenLengthUnitsAndValues_convertUnits_shouldReturnConvertedQuantity() {
        Quantity quantity = unitConverter.convert(new Quantity(10, MILLIMETER), CENTIMETER);
        assertEquals(quantity.getValue(), 1);
        assertEquals(quantity.getUnit(), CENTIMETER);
    }

    @Test
    void givenTemperatureUnitsAndValues_convertUnits_shouldReturnConvertedQuantity() {
        Quantity resultQuantity = unitConverter.convert(new Quantity(10, FAHRENHEIT), CELSIUS);
        Quantity expectedQuantity = new Quantity(-12.2222, CELSIUS);
        assertEquals(expectedQuantity, resultQuantity);
    }

    @Test
    void givenWrongUnitsAndValues_convertUnits_shouldThrowException() {
        try {
            unitConverter.convert(new Quantity(10, FEET), CELSIUS);
        }catch (UnitConversionFailedException e) {
            assertEquals("can not convert FEET to CELSIUS", e.getMessage());
        }
    }

    @Test
    void givenVolumeUnitsAndValues_convertUnits_shouldReturnConvertedQuantity() {
        Quantity resultQuantity = unitConverter.convert(new Quantity(1, CUBIC_FEET), GALLON);
        Quantity expectedQuantity = new Quantity(7.48052, GALLON);
        assertEquals(expectedQuantity, resultQuantity);
    }

    @Test
    void givenWeightUnitsAndValues_convertUnits_shouldReturnConvertedQuantity() {
        Quantity resultQuantity = unitConverter.convert(new Quantity(1, POUND), OUNCE);
        Quantity expectedQuantity = new Quantity(16, OUNCE);
        assertEquals(expectedQuantity, resultQuantity);
    }

    @Test
    void givenZeroArguments_getAllUnitTypes_shouldReturnArrayOfValidTypes() {
        String[] allUnitTypes = unitConverter.getAllUnitTypes();
        assertEquals(4, allUnitTypes.length);
    }

}
