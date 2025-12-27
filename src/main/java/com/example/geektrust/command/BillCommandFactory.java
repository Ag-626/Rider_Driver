package com.example.geektrust.command;

import com.example.geektrust.appservices.RideService;
import com.example.geektrust.cli.ParsedInput;
import java.util.Objects;

public class BillCommandFactory implements CommandFactory{

  private final RideService rideService;

  public BillCommandFactory(RideService rideService){
    this.rideService = rideService;
  }

  @Override
  public Command create(ParsedInput parsedInput){
    Objects.requireNonNull(parsedInput, "The parsed input cannot be null");

    String rideId = parsedInput.getEntityId();
    if(rideId == null || rideId.trim().isEmpty())
      throw new IllegalArgumentException("BILL requires a rideId");

    return new BillCommand(rideId, rideService);
  }

}
