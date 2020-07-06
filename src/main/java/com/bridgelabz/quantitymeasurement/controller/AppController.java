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
@RequestMapping("quantity-measurements")
public class AppController {

    @Autowired
    private UnitConverter unitConverter;

    @GetMapping("convert/{oldUnit}/{value}/{newUnit}")
    public Quantity convertUnits(@PathVariable("oldUnit") Units  oldUnit, @PathVariable("value") double value,
                                 @PathVariable("newUnit") Units  newUnit) {
        return unitConverter.convert(new Quantity(value, oldUnit), newUnit);
    }

}
