package com.step.bootcamp;

import java.util.ArrayList;

public class Attendant {
  private final ArrayList<ParkingLot> parkingLots;

  public Attendant() {
    parkingLots = new ArrayList<>();
  }

  public void add(ParkingLot parkingLot) {
    int index = parkingLots.size();
    for (ParkingLot lot : parkingLots) {
     if(!lot.isBigger(parkingLot)){
       index = parkingLots.indexOf(lot);
       break;
     }
    }
    parkingLots.add(index, parkingLot);
  }

  public Object park(Vehicle vehicle) throws CannotParkException {
    for (ParkingLot parkingLot : parkingLots) {
     if(!parkingLot.isFull()){
       return parkingLot.park(vehicle);
     }
    }
    throw new CannotParkException("Parking is full");
  }

  public Vehicle checkoutFor(Object token) throws VehicleNotFoundException {
    for (ParkingLot parkingLot : parkingLots) {
      try {
        return parkingLot.checkoutFor(token);
      } catch (VehicleNotFoundException ignored){}
    }
    throw new VehicleNotFoundException();
  }
}
