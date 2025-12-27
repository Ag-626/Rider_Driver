package com.example.geektrust.command;

import com.example.geektrust.appservices.RideService;
import com.example.geektrust.cli.ParsedInput;
import java.util.List;
import java.util.Objects;

public class StartRideCommandFactory implements CommandFactory{

  private final RideService rideService;

  public StartRideCommandFactory(RideService rideService){
    this.rideService = rideService;
  }

  @Override
  public Command create(ParsedInput parsedInput){
    Objects.requireNonNull(parsedInput, "ParsedInput is required");

    String rideId = parsedInput.getEntityId();

    List<String> args = parsedInput.getArgs();

    if(rideId == null || rideId.isEmpty()){
      throw new IllegalArgumentException("START_RIDE requires a rideId");
    }

    if(args.size()<2){
      throw new IllegalArgumentException("START_RIDE requires a N and riderId");
    }

    int N = Integer.parseInt(args.get(0));
    String riderId = args.get(1);

    return new StartRideCommand(rideId, N, riderId, rideService);

  }

}
