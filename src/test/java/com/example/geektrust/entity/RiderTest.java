package com.example.geektrust.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RiderTest {

  @Test
  void riderCanRequestThenStartRide() {
    Rider rider = new Rider("R1", new Position(0, 0));
    rider.requestRide();
    rider.startRide("ride-1");
  }

  @Test
  void requestRideWhileOnRideThrows() {
    Rider rider = new Rider("R1", new Position(0, 0));
    rider.requestRide();
    rider.startRide("ride-1");

    assertThrows(IllegalStateException.class, rider::requestRide);
  }

  @Test
  void completeRideMovesRiderBackToIdle() {
    Rider rider = new Rider("R1", new Position(1, 1));
    rider.requestRide();
    rider.startRide("ride-2");

    Position drop = new Position(5, 5);
    rider.completeRide("ride-2", drop);

    assertEquals(drop, rider.getPosition());
    rider.requestRide(); // should be allowed again after completion
  }

  @Test
  void completeRideWithWrongRideIdThrows() {
    Rider rider = new Rider("R1", new Position(0, 0));
    rider.requestRide();
    rider.startRide("ride-2");

    assertThrows(IllegalStateException.class,
        () -> rider.completeRide("wrong", new Position(1, 1)));
  }
}

