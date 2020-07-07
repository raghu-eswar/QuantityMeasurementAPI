package com.bridgelabz.quantitymeasurement.controller;

import com.bridgelabz.quantitymeasurement.enumeration.Units;
import com.bridgelabz.quantitymeasurement.model.Quantity;
import com.bridgelabz.quantitymeasurement.scrvice.UnitConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quantity-measurements")
@Api(value = "Measurement conversion controller")
public class AppController {

    @Autowired
    private UnitConverter unitConverter;

    @ApiOperation(value = "Get array of valid unit types ")
    @GetMapping(value = {"", "/"})
    public String[] getAllUnitTypes() {
        return unitConverter.getAllUnitTypes();
    }

    @ApiOperation(value = "Get array of valid units of given type types ")
    @GetMapping(value = {"/{unitType}"})
    public Units[] getValidUnitsOf(@PathVariable("unitType") String  unitType) {
        return unitConverter.getValidUnitsOf(unitType);
    }

    @ApiOperation(value = "convert value to given unit ")
    @GetMapping("convert/{oldUnit}/{value}/{newUnit}")
    public Quantity convertUnits(@PathVariable("oldUnit") Units  oldUnit, @PathVariable("value") double value,
                                 @PathVariable("newUnit") Units  newUnit) {
        return unitConverter.convert(new Quantity(value, oldUnit), newUnit);
    }

}
