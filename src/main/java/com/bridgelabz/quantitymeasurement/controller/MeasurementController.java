package com.bridgelabz.quantitymeasurement.controller;

import com.bridgelabz.quantitymeasurement.enumeration.UnitTypes;
import com.bridgelabz.quantitymeasurement.enumeration.Units;
import com.bridgelabz.quantitymeasurement.model.Quantity;
import com.bridgelabz.quantitymeasurement.dto.Response;
import com.bridgelabz.quantitymeasurement.scrvice.UnitConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("measurements")
@Api
public class MeasurementController {

    @Autowired
    private UnitConverter unitConverter;

    @ApiOperation(value = "returns array of valid unit types ")
    @GetMapping( "/")
    public ResponseEntity<Response> getAllUnitTypes() {
        return ResponseEntity.ok(new Response(unitConverter.getAllUnitTypes(), HttpStatus.OK));
    }

    @ApiOperation(value = "returns array of valid units of given type ", notes = "example - /LENGTH")
    @GetMapping(value = {"/{unitType}"})
    public ResponseEntity<Response> getValidUnitsOf(@ApiParam(name = "unit type", value = "unit type to get valid units", required = true)
                                                        @PathVariable("unitType") UnitTypes unitType) {
        return ResponseEntity.ok(new Response(unitConverter.getValidUnitsOf(unitType), HttpStatus.OK));
    }

    @ApiOperation(value = "converts value to given unit ",
                    notes = "both old and new unit should be same type /INCH/10/FEET - valid & /INCH/10/TEMPERATURE - invalid")
    @GetMapping("convert/{oldUnit}/{value}/{newUnit}")
    public ResponseEntity<Response> convertUnits(@ApiParam(name ="old unit", value = "unit to be converted", required = true)
                                                     @PathVariable("oldUnit") Units  oldUnit,
                                                 @ApiParam(value = "value to be converted", required = true)
                                                     @PathVariable("value") double value,
                                                 @ApiParam(name = "new unit", value = "unit required after conversion", required = true)
                                                     @PathVariable("newUnit") Units  newUnit) {
        return ResponseEntity.ok(new Response(unitConverter.convert(new Quantity(value, oldUnit), newUnit), HttpStatus.OK));
    }

}
