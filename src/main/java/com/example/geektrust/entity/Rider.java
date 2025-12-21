package com.example.geektrust.entity;

import java.util.Objects;

public class Rider extends Locatable {
  private final String riderId;

  public Rider(String riderId, Position position){
    super(position);
    this.riderId = Objects.requireNonNull(riderId, "Rider Id cannot be null");
  }

  public String getRiderId() {
    return riderId;
  }

}
