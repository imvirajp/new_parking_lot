package com.step.bootcamp;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AttendantTest {
  private class Car implements Vehicle {
  }
  @Test
  public void shouldBeAbleToParkCar() throws CannotParkException {
    Attendant attendant = new Attendant(new Assistant());
    attendant.add(new ParkingLot(1));
    assertNotNull(attendant.park(new Car()));
  }

  @Test(expected = CannotParkException.class)
  public void shouldBeAbleToParkCarWhenNoLotAreAdded() throws CannotParkException {
    Attendant attendant = new Attendant(new Assistant());
    assertNotNull(attendant.park(new Car()));
  }

  @Test
  public void shouldBeAbleToParkCarWhenOneLotIsFull() throws CannotParkException {
    Attendant attendant = new Attendant(new Assistant());
    attendant.add(new ParkingLot(1));
    attendant.add(new ParkingLot(1));
    attendant.park(new Car());
    assertNotNull(attendant.park(new Car()));
  }

  @Test(expected = CannotParkException.class)
  public void shouldThrowExceptionWhenAllLotsAreFull() throws CannotParkException {
    Attendant attendant = new Attendant(new Assistant());
    attendant.add(new ParkingLot(1));
    attendant.park(new Car());
    attendant.park(new Car());
  }

  @Test
  public void shouldCheckoutCar() throws CannotParkException, VehicleNotFoundException {
    Attendant attendant = new Attendant(new Assistant());
    attendant.add(new ParkingLot(1));
    Car car = new Car();
    Object token = attendant.park(car);
    assertEquals(attendant.checkoutFor(token), car);
  }


  @Test
  public void shouldNotifyEveryOneWhenALotIsFull() throws CannotParkException {
    Attendant attendant = new Attendant(new Assistant());
    ArrayList<String> messages = new ArrayList<>();
    attendant.addListener(msg -> {
      messages.add(msg);
      return "ack";
    });
    attendant.add(new ParkingLot(1));
    attendant.add(new ParkingLot(1));
    attendant.park(new Car());
    assertThat(messages, hasItem("Full"));
    assertThat(messages.size(), is(1));
  }

  @Test
  public void shouldNotifyEveryOneWhenSpaceIsAvailable() throws CannotParkException, VehicleNotFoundException {
    Attendant attendant = new Attendant(new Assistant());
    ArrayList<String> messages = new ArrayList<>();
    attendant.addListener(msg -> {
      messages.add(msg);
      return "ack";
    });

    attendant.add(new ParkingLot(1));
    Object token = attendant.park(new Car());
    attendant.checkoutFor(token);
    assertThat(messages, hasItem("Space Available"));
    assertThat(messages.size(), is(2));
  }
}