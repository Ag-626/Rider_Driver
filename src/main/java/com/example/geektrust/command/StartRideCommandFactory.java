package com.example.geektrust.command;

import com.example.geektrust.appservices.RideService;
import com.example.geektrust.cli.ParsedInput;

import java.util.List;
import java.util.Objects;

public class StartRideCommandFactory implements CommandFactory {

  private static final String CMD = "START_RIDE";
  private static final int REQUIRED_ARG_COUNT = 2;

  private final RideService rideService;

  public StartRideCommandFactory(RideService rideService) {
    this.rideService = Objects.requireNonNull(rideService, "rideService is required");
  }

  @Override
  public Command create(ParsedInput parsedInput) {
    InputUtil.requireInput(parsedInput);

    String rideId = InputUtil.requireEntityId(parsedInput, CMD);
    List<String> args = InputUtil.args(parsedInput);

    InputUtil.requireArgCount(args, REQUIRED_ARG_COUNT, CMD, "requires N and RIDER_ID");

    int n = InputUtil.requireInt(args, 0, CMD, "N");
    String riderId = InputUtil.requireStringArg(args, 1, CMD, "RIDER_ID");

    return new StartRideCommand(rideId, n, riderId, rideService);
  }
}