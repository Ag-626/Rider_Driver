package com.example.geektrust.command;

import com.example.geektrust.appservices.RideService;
import com.example.geektrust.appservices.StartRideResult;
import java.util.Objects;

public class StartRideCommand implements Command{
  private final String rideId;
  private final int N;
  private final String riderId;
  private final RideService rideService;

  public StartRideCommand(String rideId, int N, String riderId, RideService rideService) {
    this.rideId = Objects.requireNonNull(rideId, "rideId cannot be null");
    this.N = N;
    this.riderId = Objects.requireNonNull(riderId, "riderId cannot be null");
    this.rideService = Objects.requireNonNull(rideService, "rideService cannot be null");
  }

  @Override
  public void execute() {
    StartRideResult result = rideService.startRide(rideId, N, riderId);

    if (result == StartRideResult.INVALID_RIDE) {
      System.out.println("INVALID_RIDE");
    } else {
      System.out.println("RIDE_STARTED " + rideId);
    }
  }

}
