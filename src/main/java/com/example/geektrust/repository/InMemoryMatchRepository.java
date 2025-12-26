package com.example.geektrust.repository;

import com.example.geektrust.entity.MatchResult;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class InMemoryMatchRepository implements MatchRepository{

  private final Map <String, MatchResult> matches = new HashMap<>();

  @Override
  public void save (MatchResult matchResult){
    Objects.requireNonNull(matchResult, "matchResult must not be null");
    matches.put(matchResult.getRiderId(), matchResult);
  }

  @Override
  public Optional<MatchResult> getByRiderId(String riderId){
    Objects.requireNonNull(riderId, "riderId must not be null");
    return Optional.ofNullable(matches.get(riderId));
  }

  @Override
  public void clear(String riderId){
    Objects.requireNonNull(riderId, "riderId must not be null");
    matches.remove(riderId);
  }

}
