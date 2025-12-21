package com.example.geektrust.entity;

import java.util.Objects;

public class Driver extends Locatable {

  private final String  driverId;
  private boolean available = true;

  public Driver(String driverId, Position position){
    super(position);
    this.driverId = Objects.requireNonNull(driverId, "Driver Id cannot be null");
  }

  public String getDriverId() {
    return driverId;
  }

  public boolean isAvailable() {
    return available;
  }

  public void markUnavailable(){
    this.available = false;
  }
}
