package com.bridgelabz.quantitymeasurement.model;

import com.bridgelabz.quantitymeasurement.enumeration.Units;

public class Quantity {
    private double value;
    private Units unit;

    public Quantity() {    }

    public Quantity(double value, Units unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {

        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Units getUnit() {
        return unit;
    }

    public void setUnit(Units unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity that = (Quantity) o;
        return value == that.value &&
                unit == that.unit;
    }

}
