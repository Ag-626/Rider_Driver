package com.example.geektrust.entity;

import java.util.Objects;

public final class Position {

  private final int x;
  private final int y;

  public Position(int x, int y){
    this.x = x;
    this.y = y;
  }

  public int getX(){
    return this.x;
  }

  public int getY(){
    return this.y;
  }

  public double distanceTo(Position position){
    Objects.requireNonNull(position, "Position should not be null");

    int dx = position.getX()-this.x;
    int dy = position.getY()-this.y;

    return Math.sqrt(dx*dx + dy*dy);
  }
}
