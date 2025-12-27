package com.example.geektrust.appservices;

import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.MatchResult;
import com.example.geektrust.entity.Position;
import com.example.geektrust.entity.Rider;
import com.example.geektrust.repository.InMemoryDriverRepository;
import com.example.geektrust.repository.InMemoryMatchRepository;
import com.example.geektrust.repository.InMemoryRiderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MatchServiceTest {

  private DriverService driverService;
  private RiderService riderService;
  private MatchService matchService;

  @BeforeEach
  void setUp() {
    driverService = new DriverService(new InMemoryDriverRepository());
    riderService = new RiderService(new InMemoryRiderRepository());
    matchService = new MatchService(driverService, riderService, new InMemoryMatchRepository());
  }

  @Test
  void matchesNearestAvailableDriversWithinRange() {
    driverService.addDriver(new Driver("D1", new Position(0, 0)));
    driverService.addDriver(new Driver("D2", new Position(0, 4)));
    driverService.addDriver(new Driver("D3", new Position(6, 0))); // out of range
    riderService.addRider(new Rider("R1", new Position(0, 0)));

    MatchResult result = matchService.match("R1");

    assertEquals(Arrays.asList("D1", "D2"), result.getEligibleDriverIds());
    assertEquals("R1", matchService.findMatchResultByRiderId("R1").get().getRiderId());
  }

  @Test
  void matchFailsWhenRiderOnRide() {
    Rider rider = new Rider("R1", new Position(0, 0));
    riderService.addRider(rider);
    rider.requestRide();
    rider.startRide("ride-1");

    assertThrows(IllegalStateException.class, () -> matchService.match("R1"));
  }
}

