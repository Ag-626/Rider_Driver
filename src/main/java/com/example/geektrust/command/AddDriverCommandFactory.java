package com.example.geektrust.command;

import com.example.geektrust.appservices.DriverService;
import com.example.geektrust.cli.ParsedInput;
import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.Position;

import java.util.List;
import java.util.Objects;

public class AddDriverCommandFactory implements CommandFactory {

  private static final String CMD = "ADD_DRIVER";
  private static final int REQUIRED_ARG_COUNT = 2;

  private final DriverService driverService;

  public AddDriverCommandFactory(DriverService driverService) {
    this.driverService = Objects.requireNonNull(driverService, "driverService is required");
  }

  @Override
  public Command create(ParsedInput parsedInput) {
    InputUtil.requireInput(parsedInput);

    String driverId = InputUtil.requireEntityId(parsedInput, CMD);
    List<String> args = InputUtil.args(parsedInput);

    InputUtil.requireArgCount(args, REQUIRED_ARG_COUNT, CMD, "requires X and Y coordinates");

    Position pos = InputUtil.requirePositionXY(args, 0, 1, CMD);
    Driver driver = new Driver(driverId, pos);

    return new AddDriverCommand(driver, driverService);
  }
}