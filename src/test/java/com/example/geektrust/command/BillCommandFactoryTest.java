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

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BillCommandFactoryTest {

  private RideService rideService() {
    DriverService driverService = new DriverService(new InMemoryDriverRepository());
    RiderService riderService = new RiderService(new InMemoryRiderRepository());
    MatchService matchService = new MatchService(driverService, riderService, new InMemoryMatchRepository());
    return new RideService(new InMemoryRideRepository(), matchService, riderService, driverService);
  }

  @Test
  void createsBillCommand() {
    BillCommandFactory factory = new BillCommandFactory(rideService());
    ParsedInput input = new ParsedInput("BILL", "ride-1", java.util.Collections.emptyList());

    assertNotNull(factory.create(input));
  }
}

