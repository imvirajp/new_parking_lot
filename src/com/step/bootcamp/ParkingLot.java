package com.step.bootcamp;

import java.util.HashMap;

public class ParkingLot implements Comparable{
  private final HashMap<Object, Vehicle> vehicles;
  private final int capacity;

  public ParkingLot(int capacity) {
    this.capacity = capacity;
    vehicles = new HashMap<>();
  }

  public Object park(Vehicle vehicle) throws CannotParkException {
    if(has(vehicle)) throw new CannotParkException("Car is already parked");
    if(isFull()) throw new CannotParkException("Parking is full");
    Object token = vehicle.hashCode();
    vehicles.put(token, vehicle);
    return token;
  }

  private boolean has(Vehicle vehicle) {
    return vehicles.containsValue(vehicle);
  }

  public Vehicle checkoutFor(Object token) throws VehicleNotFoundException {
    Vehicle vehicle = vehicles.remove(token);
    if(vehicle == null)
      throw new VehicleNotFoundException();
    return vehicle;
  }

  public boolean isFull() {
    return vehicles.size() == capacity;
  }

  public boolean isBigger(ParkingLot other) {
    return capacity >= other.capacity;
  }

  public boolean hasCar(Object token) {
    return vehicles.containsKey(token);
  }

  @Override
  public int compareTo(Object o) {
    ParkingLot other = (ParkingLot) o;
    return Integer.compare(capacity, other.capacity);
  }
}
