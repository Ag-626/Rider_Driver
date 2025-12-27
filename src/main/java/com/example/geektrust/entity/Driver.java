package com.example.geektrust.entity;

import java.util.Objects;

public class Driver extends Locatable {

  private final String  driverId;
  private DriverStatus status = DriverStatus.AVAILABLE;

  public Driver(String driverId, Position position){
    super(position);
    this.driverId = Objects.requireNonNull(driverId, "Driver Id cannot be null");
  }

  public String getDriverId() {
    return driverId;
  }

  public boolean isUnavailable() {
    return this.status != DriverStatus.AVAILABLE;
  }

  public void assignRide(){
    if(this.status!=DriverStatus.AVAILABLE)
      throw new IllegalStateException("Driver not available");
    this.status = DriverStatus.ON_RIDE;
  }
  public void completeRide(Position dropPosition){
    moveTo(dropPosition);
    this.status = DriverStatus.AVAILABLE;
  }

}
