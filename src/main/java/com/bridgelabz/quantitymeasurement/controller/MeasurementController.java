package com.bridgelabz.quantitymeasurement.controller;

import com.bridgelabz.quantitymeasurement.enumeration.UnitTypes;
import com.bridgelabz.quantitymeasurement.enumeration.Units;
import com.bridgelabz.quantitymeasurement.model.Quantity;
import com.bridgelabz.quantitymeasurement.response.Response;
import com.bridgelabz.quantitymeasurement.scrvice.UnitConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("measurements")
@Api(value = "Measurement conversion controller")
public class MeasurementController {

    @Autowired
    private UnitConverter unitConverter;

    @ApiOperation(value = "returns array of valid unit types ")
    @GetMapping( "/")
    public ResponseEntity<Response> getAllUnitTypes() {
        return ResponseEntity.ok(new Response(unitConverter.getAllUnitTypes(), HttpStatus.OK));
    }

    @ApiOperation(value = "returns array of valid units of given type ")
    @GetMapping(value = {"/{unitType}"})
    public ResponseEntity<Response> getValidUnitsOf(@PathVariable("unitType") UnitTypes unitType) {
        return ResponseEntity.ok(new Response(unitConverter.getValidUnitsOf(unitType), HttpStatus.OK));
    }

    @ApiOperation(value = "converts value to given unit ", notes = "to convert 10inch to feet example path variables INCH/10/FEET")
    @GetMapping("convert/{oldUnit}/{value}/{newUnit}")
    public ResponseEntity<Response> convertUnits(@PathVariable("oldUnit") Units  oldUnit, @PathVariable("value") double value,
                                                 @PathVariable("newUnit") Units  newUnit) {
        return ResponseEntity.ok(new Response(unitConverter.convert(new Quantity(value, oldUnit), newUnit), HttpStatus.OK));
    }

}
