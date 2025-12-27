package com.example.geektrust.repository;

import com.example.geektrust.entity.Position;
import com.example.geektrust.entity.Rider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryRiderRepositoryTest {

  @Test
  void saveAndFindRider() {
    InMemoryRiderRepository repo = new InMemoryRiderRepository();
    Rider rider = new Rider("R1", new Position(3, 4));

    repo.save(rider);

    assertTrue(repo.findById("R1").isPresent());
    assertEquals(rider, repo.findById("R1").get());
  }
}

