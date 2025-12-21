package com.example.geektrust.entity;

import java.util.Objects;

public class Rider extends Locatable {
  private final String riderId;
  private RiderStatus status = RiderStatus.IDLE;
  private String activeRideId;

  public Rider(String riderId, Position position){
    super(position);
    this.riderId = Objects.requireNonNull(riderId, "Rider Id cannot be null");
  }

  public String getRiderId() {
    return this.riderId;
  }

  public RiderStatus getStatus() {
    return this.status;
  }

  public boolean isIdle(){
    return this.status == RiderStatus.IDLE;
  }

  public boolean isOnRide(){
    return this.status == RiderStatus.ON_RIDE;
  }

  public boolean isLookingForRide(){
    return this.status == RiderStatus.LOOKING_FOR_RIDE;
  }

  public void requestRide(){
    if(status!=RiderStatus.IDLE)
      throw new IllegalStateException("Rider must be IDLE to request a ride");
    this.status = RiderStatus.LOOKING_FOR_RIDE;
  }

  public void startRide(String rideId){
    Objects.requireNonNull(rideId, "Ride Id can't be null");
    if(this.status != RiderStatus.LOOKING_FOR_RIDE)
      throw new IllegalStateException("Rider must be looking for the ride to start a ride");
    this.activeRideId = rideId;
    this.status = RiderStatus.ON_RIDE;
  }

  public void completeRide(String rideId, Position dropPosition){
    Objects.requireNonNull(rideId, "Ride Id can't be null");
    Objects.requireNonNull(dropPosition, "drop position must not be null");

    if(status != RiderStatus.ON_RIDE)
      throw new IllegalStateException("Rider must be on ride to complete the ride");

    if(this.activeRideId != rideId)
      throw new IllegalStateException("Ride Id mismatch. Active ride: " + activeRideId);

    moveTo(dropPosition);
    status = RiderStatus.IDLE;
    activeRideId = null;
  }

  public String getActiveRideId(){
    return this.activeRideId;
  }

}
