package com.example.geektrust.command;

import com.example.geektrust.appservices.MatchService;
import com.example.geektrust.entity.MatchResult;

import java.util.List;
import java.util.Objects;

public class MatchCommand implements Command {

  private final String riderId;
  private final MatchService matchService;

  public MatchCommand(String riderId, MatchService matchService) {
    this.riderId = Objects.requireNonNull(riderId, "riderId cannot be null");
    this.matchService = Objects.requireNonNull(matchService, "matchService cannot be null");
  }

  @Override
  public void execute() {
    MatchResult result = matchService.match(riderId);

    List<String> ids = result.getEligibleDriverIds();
    if (ids.isEmpty()) {
      System.out.println("NO_DRIVERS_AVAILABLE");
      return;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < ids.size(); i++) {
      if (i > 0) sb.append(" ");
      sb.append(ids.get(i));
    }
    System.out.println("DRIVERS_MATCHED " + sb);
  }
}