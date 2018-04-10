package com.step.bootcamp;

import java.util.*;

public class Attendant {
  private final ArrayList<ParkingLot> parkingLots;
  private final ArrayList<ParkingLot> availableLots;

  public Attendant() {
    parkingLots = new ArrayList<>();
    availableLots = new ArrayList<>();
  }

  public void add(ParkingLot parkingLot) {
    parkingLots.add(getInsertIndex(parkingLot, parkingLots), parkingLot);
    availableLots.add(getInsertIndex(parkingLot,availableLots),parkingLot);
  }

  private int getInsertIndex( ParkingLot parkingLot, ArrayList<ParkingLot> parkingLots){
    int index = parkingLots.size();
    for (ParkingLot lot : parkingLots) {
     if(!lot.isBigger(parkingLot)){
       index = parkingLots.indexOf(lot);
       break;
     }
    }
    return index;
  }

  public Object park(Vehicle vehicle) throws CannotParkException {
    if(availableLots.isEmpty())
      throw new CannotParkException("Parking is full");
    ParkingLot lot = availableLots.get(0);
    Object token = lot.park(vehicle);
    if(lot.isFull()) availableLots.remove(lot);
    return token;
  }

  public Vehicle checkoutFor(Object token) throws VehicleNotFoundException {
    for (ParkingLot parkingLot : parkingLots) {
      try {
        Vehicle vehicle = parkingLot.checkoutFor(token);
        if(!availableLots.contains(parkingLot)){
          availableLots.add(getInsertIndex(parkingLot,availableLots),parkingLot);
        }
        return vehicle;
      } catch (VehicleNotFoundException ignored){}
    }
    throw new VehicleNotFoundException();
  }
}
