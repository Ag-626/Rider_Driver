package com.example.geektrust.command;

import com.example.geektrust.appservices.RideService;
import com.example.geektrust.cli.ParsedInput;
import com.example.geektrust.entity.Position;
import java.util.List;
import java.util.Objects;

public class StopRideCommandFactory implements CommandFactory{

  private final RideService rideService;

  public StopRideCommandFactory(RideService rideService){
    this.rideService = rideService;
  }

  public Command create(ParsedInput parsedInput){
    Objects.requireNonNull(parsedInput, "ParsedInput is required");

    String rideId = parsedInput.getEntityId();

    List<String> args = parsedInput.getArgs();

    if(rideId == null || rideId.isEmpty()){
      throw new IllegalArgumentException("STOP_RIDE requires a rideId");
    }

    if(args.size()<3){
      throw new IllegalArgumentException("STOP_RIDE requires a destinationPosition and timeTaken");
    }

    int X = Integer.parseInt(args.get(0));
    int Y = Integer.parseInt(args.get(1));
    int timeTaken = Integer.parseInt(args.get(2));


    return new StopRideCommand(rideId, new Position(X, Y), timeTaken, rideService);
  }

}
