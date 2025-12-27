package com.example.geektrust.command;

import com.example.geektrust.appservices.RideService;
import com.example.geektrust.cli.ParsedInput;

import java.util.Objects;

public class BillCommandFactory implements CommandFactory {

  private static final String CMD = "BILL";
  private final RideService rideService;

  public BillCommandFactory(RideService rideService) {
    this.rideService = Objects.requireNonNull(rideService, "rideService is required");
  }

  @Override
  public Command create(ParsedInput parsedInput) {
    InputUtil.requireInput(parsedInput);

    String rideId = InputUtil.requireEntityId(parsedInput, CMD);

    return new BillCommand(rideId, rideService);
  }
}