package com.example.geektrust.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MatchResult {

  private final String riderId;
  private final List<String> eligibleDriverIds;

  public MatchResult(String riderId, List<String> eligibleDriverIds){
    this.riderId = Objects.requireNonNull(riderId, "Rider Id is must to match");
    Objects.requireNonNull(eligibleDriverIds, "eligibleDriverIds must not be null");
    this.eligibleDriverIds =
        Collections.unmodifiableList(new ArrayList<>(eligibleDriverIds));
  }

  public String getRiderId(){
    return this.riderId;
  }

  public List<String> getEligibleDriverIds(){
    return this.eligibleDriverIds;
  }

}
