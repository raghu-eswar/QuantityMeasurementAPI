package com.bridgelabz.quantitymeasurement.enumeration;

public enum Units {


//    Valid length units
    INCH(25.4,  "LENGTH"), FEET(304.8,  "LENGTH"), YARD(914.4,  "LENGTH"),
    MILLIMETER(1,  "LENGTH"), MILE( 1609344,  "LENGTH"), CENTIMETER( 10, "LENGTH"),
    METER( 1000,  "LENGTH"), KILOMETER( 1000000,  "LENGTH"),

//    Valid temperature units
    CELSIUS(1, 273.15, "TEMPERATURE"), KELVIN(1, 0, "TEMPERATURE"),
    FAHRENHEIT(0.5555555556, 255.37222222222, "TEMPERATURE");

    public double multiplicand;
    public double addend;
    public String type;

    Units(double multiplicand, double addend, String type) {
        this(multiplicand, type);
        this.addend = addend;
    }

    Units(double multiplicand, String type) {
        this.multiplicand = multiplicand;
        this.type = type;
    }
}
