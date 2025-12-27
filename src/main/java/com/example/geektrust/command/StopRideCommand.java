package com.example.geektrust.command;

import com.example.geektrust.appservices.RideService;
import com.example.geektrust.appservices.StopRideResult;
import com.example.geektrust.entity.Position;

public class StopRideCommand implements Command{

  private final String rideId;
  private final Position destinationPosition;
  private final int timeTaken;
  private final RideService rideService;

  public StopRideCommand(String rideId, Position destinationPosition, int timeTaken, RideService rideService){
    this.rideId = rideId;
    this.destinationPosition = destinationPosition;
    this.timeTaken = timeTaken;
    this.rideService = rideService;
  }

  @Override
  public void execute(){
    StopRideResult result = rideService.stopRide(rideId, destinationPosition, timeTaken);

    if (result == StopRideResult.INVALID_RIDE) {
      System.out.println("INVALID_RIDE");
    } else {
      System.out.println("RIDE_STOPPED " + rideId);
    }
  }

}
