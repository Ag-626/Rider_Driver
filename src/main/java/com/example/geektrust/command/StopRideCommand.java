package com.example.geektrust.command;

import com.example.geektrust.appservices.RideService;
import com.example.geektrust.appservices.StopRideResult;
import com.example.geektrust.entity.Position;

import java.util.Objects;

public class StopRideCommand implements Command {

  private final String rideId;
  private final Position destinationPosition;
  private final int timeTaken;
  private final RideService rideService;

  public StopRideCommand(String rideId, Position destinationPosition, int timeTaken, RideService rideService) {
    this.rideId = Objects.requireNonNull(rideId, "rideId cannot be null");
    this.destinationPosition = Objects.requireNonNull(destinationPosition, "destinationPosition cannot be null");
    this.timeTaken = timeTaken;
    this.rideService = Objects.requireNonNull(rideService, "rideService cannot be null");
  }

  @Override
  public void execute() {
    StopRideResult result = rideService.stopRide(rideId, destinationPosition, timeTaken);

    if (result == StopRideResult.INVALID_RIDE) {
      System.out.println("INVALID_RIDE");
      return;
    }

    System.out.println("RIDE_STOPPED " + rideId);
  }
}