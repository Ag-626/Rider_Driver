package com.example.geektrust.command;

import com.example.geektrust.appservices.RiderService;
import com.example.geektrust.cli.ParsedInput;
import com.example.geektrust.entity.Position;
import com.example.geektrust.entity.Rider;

import java.util.List;
import java.util.Objects;

public class AddRiderCommandFactory implements CommandFactory {

  private static final String CMD = "ADD_RIDER";
  private static final int REQUIRED_ARG_COUNT = 2;

  private final RiderService riderService;

  public AddRiderCommandFactory(RiderService riderService) {
    this.riderService = Objects.requireNonNull(riderService, "riderService is required");
  }

  @Override
  public Command create(ParsedInput parsedInput) {
    InputUtil.requireInput(parsedInput);

    String riderId = InputUtil.requireEntityId(parsedInput, CMD);
    List<String> args = InputUtil.args(parsedInput);

    InputUtil.requireArgCount(args, REQUIRED_ARG_COUNT, CMD, "requires X and Y coordinates");

    Position pos = InputUtil.requirePositionXY(args, 0, 1, CMD);
    Rider rider = new Rider(riderId, pos);

    return new AddRiderCommand(rider, riderService);
  }
}