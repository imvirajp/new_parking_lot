package com.step.bootcamp;

import org.junit.Test;

import static org.junit.Assert.*;

public class AttendantTest {
  private class Car implements Vehicle {
  }
  @Test
  public void shouldBeAbleToParkCar() throws CannotParkException {
    Attendant attendant = new Attendant();
    attendant.add(new ParkingLot(1));
    assertNotNull(attendant.park(new Car()));
  }

  @Test(expected = CannotParkException.class)
  public void shouldBeAbleToParkCarWhenNoLotAreAdded() throws CannotParkException {
    Attendant attendant = new Attendant();
    assertNotNull(attendant.park(new Car()));
  }

  @Test
  public void shouldBeAbleToParkCarWhenOneLotIsFull() throws CannotParkException {
    Attendant attendant = new Attendant();
    attendant.add(new ParkingLot(1));
    attendant.add(new ParkingLot(1));
    attendant.park(new Car());
    assertNotNull(attendant.park(new Car()));
  }

  @Test(expected = CannotParkException.class)
  public void shouldThrowExceptionWhenAllLotsAreFull() throws CannotParkException {
    Attendant attendant = new Attendant();
    attendant.add(new ParkingLot(1));
    attendant.park(new Car());
    attendant.park(new Car());
  }

  @Test
  public void shouldCheckoutCar() throws CannotParkException, VehicleNotFoundException {
    Attendant attendant = new Attendant();
    attendant.add(new ParkingLot(1));
    Car car = new Car();
    Object token = attendant.park(car);
    assertEquals(attendant.checkoutFor(token), car);
  }

  @Test
  public void shouldParkCarToHighestCapacityLot() throws CannotParkException, VehicleNotFoundException {
    Attendant attendant = new Attendant();
    ParkingLot parkingLot3 = new ParkingLot(3);
    attendant.add(parkingLot3);
    attendant.add(new ParkingLot(1));
    attendant.add(new ParkingLot(2));
    ParkingLot parkingLot4 = new ParkingLot(4);
    attendant.add(parkingLot4);
    Car car = new Car();
    Object token = attendant.park(car);
    attendant.park(new Car());
    attendant.park(new Car());
    attendant.park(new Car());

    Object token1 = attendant.park(new Car());
    assertTrue(parkingLot4.hasCar(token));
    assertTrue(parkingLot4.isFull());
    assertTrue(parkingLot3.hasCar(token1));
  }
}
