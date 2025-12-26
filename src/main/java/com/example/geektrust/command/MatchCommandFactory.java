package com.example.geektrust.command;

import com.example.geektrust.appservices.MatchService;
import com.example.geektrust.cli.ParsedInput;
import java.util.Objects;

public class MatchCommandFactory implements CommandFactory{

  private final MatchService matchService;

  public MatchCommandFactory(MatchService matchService){
    this.matchService = Objects.requireNonNull(matchService, "match service cannot be null");
  }

  @Override
  public Command create(ParsedInput input){
    String riderId = input.getEntityId();
    if(riderId == null || riderId.trim().isEmpty()){
      throw new IllegalArgumentException("Rider Id is null");
    }

    return new MatchCommand(riderId, matchService);
  }
}
