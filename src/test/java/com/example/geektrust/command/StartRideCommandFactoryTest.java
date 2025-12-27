package com.example.geektrust.command;

import com.example.geektrust.appservices.DriverService;
import com.example.geektrust.appservices.MatchService;
import com.example.geektrust.appservices.RideService;
import com.example.geektrust.appservices.RiderService;
import com.example.geektrust.cli.ParsedInput;
import com.example.geektrust.repository.InMemoryDriverRepository;
import com.example.geektrust.repository.InMemoryMatchRepository;
import com.example.geektrust.repository.InMemoryRideRepository;
import com.example.geektrust.repository.InMemoryRiderRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StartRideCommandFactoryTest {

  private RideService rideService() {
    DriverService driverService = new DriverService(new InMemoryDriverRepository());
    RiderService riderService = new RiderService(new InMemoryRiderRepository());
    MatchService matchService = new MatchService(driverService, riderService, new InMemoryMatchRepository());
    return new RideService(new InMemoryRideRepository(), matchService, riderService, driverService);
  }

  @Test
  void createsStartRideCommand() {
    StartRideCommandFactory factory = new StartRideCommandFactory(rideService());
    ParsedInput input = new ParsedInput("START_RIDE", "ride-1", Arrays.asList("1", "R1"));
    assertNotNull(factory.create(input));
  }

  @Test
  void missingArgsThrows() {
    StartRideCommandFactory factory = new StartRideCommandFactory(rideService());
    ParsedInput input = new ParsedInput("START_RIDE", "ride-1", Arrays.asList("1"));
    assertThrows(IllegalArgumentException.class, () -> factory.create(input));
  }
}

