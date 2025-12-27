package com.example.geektrust.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RideInfoTest {

  @Test
  void stopStoresTimeDistanceAndStatus() {
    Position source = new Position(0, 0);
    RideInfo rideInfo = new RideInfo("ride-1", "D1", "R1", source);

    Position dest = new Position(3, 4);
    rideInfo.stop(dest, 10);

    assertTrue(rideInfo.isRideStopped());
    assertEquals(10, rideInfo.getTimeTaken());
    assertEquals(5.0, rideInfo.distanceTravelled());
  }

  @Test
  void stopTwiceThrows() {
    RideInfo rideInfo = new RideInfo("ride-1", "D1", "R1", new Position(0, 0));
    rideInfo.stop(new Position(1, 1), 5);

    assertThrows(IllegalStateException.class,
        () -> rideInfo.stop(new Position(2, 2), 6));
  }

  @Test
  void stopWithNegativeTimeThrows() {
    RideInfo rideInfo = new RideInfo("ride-1", "D1", "R1", new Position(0, 0));

    assertThrows(IllegalArgumentException.class,
        () -> rideInfo.stop(new Position(2, 2), -1));
  }
}

