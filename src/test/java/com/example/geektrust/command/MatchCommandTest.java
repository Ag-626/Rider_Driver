package com.example.geektrust.command;

import com.example.geektrust.appservices.DriverService;
import com.example.geektrust.appservices.MatchService;
import com.example.geektrust.appservices.RiderService;
import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.Position;
import com.example.geektrust.entity.Rider;
import com.example.geektrust.repository.InMemoryDriverRepository;
import com.example.geektrust.repository.InMemoryMatchRepository;
import com.example.geektrust.repository.InMemoryRiderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchCommandTest {

  private DriverService driverService;
  private RiderService riderService;
  private MatchService matchService;

  @BeforeEach
  void init() {
    driverService = new DriverService(new InMemoryDriverRepository());
    riderService = new RiderService(new InMemoryRiderRepository());
    matchService = new MatchService(driverService, riderService, new InMemoryMatchRepository());
  }

  @Test
  void printsMatchedDrivers() {
    driverService.addDriver(new Driver("D1", new Position(0, 1)));
    riderService.addRider(new Rider("R1", new Position(0, 0)));

    String output = captureOutput(() -> new MatchCommand("R1", matchService).execute());

    assertEquals("DRIVERS_MATCHED D1", output.trim());
  }

  @Test
  void printsNoDriversAvailable() {
    riderService.addRider(new Rider("R1", new Position(0, 0)));

    String output = captureOutput(() -> new MatchCommand("R1", matchService).execute());

    assertEquals("NO_DRIVERS_AVAILABLE", output.trim());
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

