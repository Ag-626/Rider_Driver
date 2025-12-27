package com.example.geektrust.appservices;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ServicesTest {

  @Test
  void createDefaultBuildsAllServices() {
    Services services = Services.createDefault();
    assertNotNull(services.driverService());
    assertNotNull(services.riderService());
    assertNotNull(services.matchService());
    assertNotNull(services.rideService());
  }
}

