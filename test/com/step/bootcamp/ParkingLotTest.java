package com.step.bootcamp;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingLotTest {
  private ParkingLot parkingLot;

  private class Car implements Vehicle {
  }

  @Before
  public void setUp() {
    parkingLot = new ParkingLot(2);
  }

  @Test
  public void shouldCheckoutTheCarOfGivenNumber() throws VehicleNotFoundException, CannotParkException {
    Car car = new Car();
    Object token = parkingLot.park(car);
    assertThat(parkingLot.checkoutFor(token), is(car));
  }

  @Test
  public void shouldBeAbleParkACar() throws CannotParkException {
    assertNotNull(parkingLot.park(new Car()));
  }

  @Test(expected = VehicleNotFoundException.class)
  public void checkedOutCarShouldNotPresent() throws CannotParkException, VehicleNotFoundException {
    Car car = new Car();
    Object token = parkingLot.park(car);
    parkingLot.checkoutFor(token);
    assertNull(parkingLot.checkoutFor(token));
  }

  @Test(expected = VehicleNotFoundException.class)
  public void shouldThrowExceptionWhenCarIsNotPresent() throws VehicleNotFoundException {
    parkingLot.checkoutFor(123);
  }

  @Test(expected = CannotParkException.class)
  public void shouldNotParkSameCarAgain() throws CannotParkException {
    Car car = new Car();
    parkingLot.park(car);
    parkingLot.park(car);
  }

  @Test
  public void shouldReturnFalseIfParkingIsNotFull() {
    assertFalse(parkingLot.isFull());
  }

  @Test
  public void shouldReturnTrueIfParkingIsFull() throws CannotParkException {
    parkingLot.park(new Car());
    parkingLot.park(new Car());
    assertTrue(parkingLot.isFull());
  }

  @Test(expected = CannotParkException.class)
  public void shouldNotParkTheCarWhenItIsFull() throws CannotParkException {
    parkingLot.park(new Car());
    parkingLot.park(new Car());
    parkingLot.park(new Car());
  }

  @Test
  public void shouldReturnDifferenceBetweenCapacity() {
    assertTrue(parkingLot.isBigger(new ParkingLot(1)));
    assertFalse(parkingLot.isBigger(new ParkingLot(3)));
    assertTrue(parkingLot.isBigger(new ParkingLot(2)));
  }
}
