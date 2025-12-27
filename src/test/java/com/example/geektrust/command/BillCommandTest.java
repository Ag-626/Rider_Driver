package com.example.geektrust.command;

import com.example.geektrust.appservices.DriverService;
import com.example.geektrust.appservices.MatchService;
import com.example.geektrust.appservices.RideService;
import com.example.geektrust.appservices.RiderService;
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

class BillCommandTest {

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
  void printsBillForCompletedRide() {
    driverService.addDriver(new Driver("D1", new Position(0, 1)));
    riderService.addRider(new Rider("R1", new Position(0, 0)));
    matchService.match("R1");
    rideService.startRide("ride-1", 1, "R1");
    rideService.stopRide("ride-1", new Position(3, 4), 10);

    String output = captureOutput(() -> new BillCommand("ride-1", rideService).execute());

    assertEquals("BILL ride-1 D1 123.00", output.trim());
  }

  @Test
  void printsInvalidWhenRideMissing() {
    String output = captureOutput(() -> new BillCommand("missing", rideService).execute());
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

