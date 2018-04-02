package com.step.bootcamp;

import java.util.Objects;

public class Measurement {
  private final double value;
  private final Unit unit;

  public Measurement(double value, Unit unit) {
    this.value = value;
    this.unit = unit;
  }

  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    if (another == null || getClass() != another.getClass()) return false;
    Measurement measurement = (Measurement) another;
    if(!measurement.unit.isSameType(unit)) return false;
    return Double.compare(measurement.unit.toBaseUnit(measurement.value), unit.toBaseUnit(value)) == 0;
  }

  @Override
  public String toString() {
    return "Measurement{" +
        "value=" + value +
        ", unit=" + unit +
        '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(unit.toBaseUnit(value));
  }
}
