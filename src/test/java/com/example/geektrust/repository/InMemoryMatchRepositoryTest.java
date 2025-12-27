package com.example.geektrust.repository;

import com.example.geektrust.entity.MatchResult;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryMatchRepositoryTest {

  @Test
  void saveFindAndClearMatch() {
    InMemoryMatchRepository repo = new InMemoryMatchRepository();
    MatchResult result = new MatchResult("R1", Arrays.asList("D1", "D2"));

    repo.save(result);
    assertTrue(repo.findByRiderId("R1").isPresent());

    repo.clear("R1");
    assertFalse(repo.findByRiderId("R1").isPresent());
  }
}

