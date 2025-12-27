package com.example.geektrust.entity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MatchResultTest {

  @Test
  void storesRiderIdAndEligibleDrivers() {
    MatchResult result = new MatchResult("R1", Arrays.asList("D1", "D2"));
    assertEquals("R1", result.getRiderId());
    assertEquals(Arrays.asList("D1", "D2"), result.getEligibleDriverIds());
  }

  @Test
  void eligibleDriverIdsAreImmutable() {
    MatchResult result = new MatchResult("R1", Arrays.asList("D1"));
    List<String> ids = result.getEligibleDriverIds();
    assertThrows(UnsupportedOperationException.class, () -> ids.add("D2"));
  }
}

