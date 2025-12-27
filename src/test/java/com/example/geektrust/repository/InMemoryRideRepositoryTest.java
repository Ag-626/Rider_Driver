package com.example.geektrust.repository;

import com.example.geektrust.entity.Position;
import com.example.geektrust.entity.RideInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryRideRepositoryTest {

  @Test
  void saveRideAndRetrieveById() {
    InMemoryRideRepository repo = new InMemoryRideRepository();
    RideInfo rideInfo = new RideInfo("ride-1", "D1", "R1", new Position(0, 0));

    repo.save(rideInfo);

    assertTrue(repo.isRideIdExist("ride-1"));
    assertTrue(repo.getByRideId("ride-1").isPresent());
    assertFalse(repo.isRideIdExist("missing"));
  }
}

