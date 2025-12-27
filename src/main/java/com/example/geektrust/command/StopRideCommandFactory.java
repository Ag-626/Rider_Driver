package com.example.geektrust.command;

import com.example.geektrust.appservices.RideService;
import com.example.geektrust.cli.ParsedInput;
import com.example.geektrust.entity.Position;

import java.util.List;
import java.util.Objects;

public class StopRideCommandFactory implements CommandFactory {

  private static final String CMD = "STOP_RIDE";
  private static final int REQUIRED_ARG_COUNT = 3;

  private final RideService rideService;

  public StopRideCommandFactory(RideService rideService) {
    this.rideService = Objects.requireNonNull(rideService, "rideService is required");
  }

  @Override
  public Command create(ParsedInput parsedInput) {
    InputUtil.requireInput(parsedInput);

    String rideId = InputUtil.requireEntityId(parsedInput, CMD);
    List<String> args = InputUtil.args(parsedInput);

    InputUtil.requireArgCount(args, REQUIRED_ARG_COUNT, CMD, "requires DEST_X DEST_Y TIME_TAKEN_IN_MIN");

    Position destination = InputUtil.requirePositionXY(args, 0, 1, CMD);
    int timeTaken = InputUtil.requireInt(args, 2, CMD, "TIME_TAKEN_IN_MIN");

    return new StopRideCommand(rideId, destination, timeTaken, rideService);
  }
}