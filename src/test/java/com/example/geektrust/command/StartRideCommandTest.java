package com.example.geektrust.command;

import com.example.geektrust.appservices.DriverService;
import com.example.geektrust.appservices.MatchService;
import com.example.geektrust.appservices.RideService;
import com.example.geektrust.appservices.RiderService;
import com.example.geektrust.appservices.Services;
import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.Position;
import com.example.geektrust.entity.Rider;
import com.example.geektrust.repository.InMemoryDriverRepository;
import com.example.geektrust.repository.InMemoryMatchRepository;
import com.example.geektrust.repository.InMemoryRideRepository;
import com.example.geektrust.repository.InMemoryRiderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StartRideCommandTest {

  private RideService rideService;
  private MatchService matchService;
  private RiderService riderService;
  private DriverService driverService;

  @BeforeEach
  void setUp() {
    driverService = new DriverService(new InMemoryDriverRepository());
    riderService = new RiderService(new InMemoryRiderRepository());
    matchService = new MatchService(driverService, riderService, new InMemoryMatchRepository());
    rideService = new RideService(new InMemoryRideRepository(), matchService, riderService, driverService);
  }

  @Test
  void printsRideStartedOnSuccess() {
    driverService.addDriver(new Driver("D1", new Position(0, 0)));
    riderService.addRider(new Rider("R1", new Position(0, 0)));
    matchService.match("R1");

    String output = captureOutput(() -> new StartRideCommand("ride-1", 1, "R1", rideService).execute());

    assertEquals("RIDE_STARTED ride-1", output.trim());
  }

  @Test
  void printsInvalidRideWhenNoMatch() {
    Services services = Services.createDefault();
    String output = captureOutput(() -> new StartRideCommand("ride-2", 1, "R1", services.rideService()).execute());
    assertEquals("INVALID_RIDE", output.trim());
  }

  private String captureOutput(Runnable runnable) {
    PrintStream original = System.out;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    System.setOut(new PrintStream(baos));
    try {
      runnable.run();
      return baos.toString();
    } finally {
      System.setOut(original);
    }
  }
}

