package com.example.geektrust.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DriverTest {

  @Test
  void assignRideMarksDriverUnavailable() {
    Driver driver = new Driver("D1", new Position(0, 0));

    assertFalse(driver.isUnavailable());
    driver.assignRide();
    assertTrue(driver.isUnavailable());
  }

  @Test
  void assignRideWhenUnavailableThrows() {
    Driver driver = new Driver("D1", new Position(1, 1));
    driver.assignRide();

    assertThrows(IllegalStateException.class, driver::assignRide);
  }

  @Test
  void completeRideResetsAvailabilityAndMovesDriver() {
    Driver driver = new Driver("D1", new Position(1, 1));
    driver.assignRide();

    Position drop = new Position(2, 3);
    driver.completeRide(drop);

    assertFalse(driver.isUnavailable());
    assertEquals(drop, driver.getPosition());
  }
}

