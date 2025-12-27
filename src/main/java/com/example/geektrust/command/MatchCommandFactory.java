package com.example.geektrust.command;

import com.example.geektrust.appservices.MatchService;
import com.example.geektrust.cli.ParsedInput;

import java.util.Objects;

public class MatchCommandFactory implements CommandFactory {

  private static final String CMD = "MATCH";
  private final MatchService matchService;

  public MatchCommandFactory(MatchService matchService) {
    this.matchService = Objects.requireNonNull(matchService, "matchService cannot be null");
  }

  @Override
  public Command create(ParsedInput input) {
    InputUtil.requireInput(input);

    String riderId = InputUtil.requireEntityId(input, CMD);

    return new MatchCommand(riderId, matchService);
  }
}