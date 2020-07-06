package com.bridgelabz.quantitymeasurement.controller;

import com.bridgelabz.quantitymeasurement.enumeration.Units;
import com.bridgelabz.quantitymeasurement.model.Quantity;
import com.bridgelabz.quantitymeasurement.scrvice.UnitConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("QuantityMeasurement")
public class AppController {

    @Autowired
    private UnitConverter unitConverter;

    @GetMapping("Convert/{oldUnit}/{value}/{newUnit}")
    public Quantity convertUnits(@PathVariable("oldUnit") String  oldUnit, @PathVariable("value") double value,
                                 @PathVariable("newUnit") String  newUnit) {
        return unitConverter.convert(new Quantity(value, Units.valueOf(oldUnit.toUpperCase())),
                                        Units.valueOf(newUnit.toUpperCase()));
    }

}
