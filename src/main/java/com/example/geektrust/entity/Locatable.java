package com.example.geektrust.entity;

import java.util.Objects;

public abstract class Locatable {
  protected Position position;

  protected Locatable(Position position){
    this.position = Objects.requireNonNull(position, "Position can't be null");
  }

  public Position getPosition(){
    return this.position;
  }

  public void moveTo(Position newPosition){
    this.position = Objects.requireNonNull(newPosition, "New Position can't be null");
  }

}
