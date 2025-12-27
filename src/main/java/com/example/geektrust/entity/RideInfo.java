package com.example.geektrust.entity;

import java.util.Objects;

public class RideInfo {

  private final String rideId;
  private final String driverId;
  private final String riderId;
  private RideStatus rideStatus;
  private final Position sourcePosition;
  private Position destinationPosition;
  private int timeTaken;

  public RideInfo(String rideId, String driverId, String riderId, Position sourcePosition) {
    this.rideId = Objects.requireNonNull(rideId, "rideId cannot be null");
    this.driverId = Objects.requireNonNull(driverId, "driverId cannot be null");
    this.riderId = Objects.requireNonNull(riderId, "riderId cannot be null");
    this.sourcePosition = Objects.requireNonNull(sourcePosition, "sourcePosition cannot be null");
    this.rideStatus = RideStatus.STARTED;
  }

  public String getRideId(){
    return this.rideId;
  }

  public boolean isRideStopped(){
    return this.rideStatus == RideStatus.STOPPED;
  }

  public String getRiderId(){
    return this.riderId;
  }

  public String getDriverId(){
    return this.driverId;
  }

  public void stop(Position destinationPosition, int timeTakenInMin) {
    if (rideStatus == RideStatus.STOPPED) {
      throw new IllegalStateException("Ride already stopped: " + rideId);
    }
    this.destinationPosition = Objects.requireNonNull(destinationPosition, "destinationPosition cannot be null");
    if (timeTakenInMin < 0) {
      throw new IllegalArgumentException("timeTakenInMin cannot be negative");
    }
    this.timeTaken = timeTakenInMin;
    this.rideStatus = RideStatus.STOPPED;
  }

  public int getTimeTaken() {
    return timeTaken;
  }
  public double distanceTravelled(){
    return (sourcePosition.distanceTo(destinationPosition));
  }
}
