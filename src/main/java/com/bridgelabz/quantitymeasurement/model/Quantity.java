package com.bridgelabz.quantitymeasurement.model;

import com.bridgelabz.quantitymeasurement.enumeration.Units;

public class Quantity {
    private double value;
    private Units unit;
    private double baseValue;

    public Quantity() {    }

    public Quantity(double value, Units unit) {
        this.value = value;
        this.unit = unit;
        setBaseValue();
    }

    public double getBaseValue() {
        return baseValue;
    }

    private void setBaseValue() {
        this.baseValue = (this.unit.multiplicand * this.value) + this.unit.addend;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
        setBaseValue();
    }

    public Units getUnit() {
        return unit;
    }

    public void setUnit(Units unit) {
        this.unit = unit;
        setBaseValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity that = (Quantity) o;
        return Math.abs(that.baseValue - this.baseValue) <= Math.min(4, (Math.min(that.baseValue, this.baseValue) * 0.0001));
    }

}
