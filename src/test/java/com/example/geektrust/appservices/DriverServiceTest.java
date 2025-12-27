package com.example.geektrust.appservices;

import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.Position;
import com.example.geektrust.repository.InMemoryDriverRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DriverServiceTest {

  @Test
  void addAndFetchDriver() {
    DriverService service = new DriverService(new InMemoryDriverRepository());
    Driver driver = new Driver("D1", new Position(1, 1));

    service.addDriver(driver);

    assertEquals(driver, service.getDriver("D1"));
    assertEquals(1, service.getAllDrivers().size());
  }

  @Test
  void addingDuplicateDriverThrows() {
    DriverService service = new DriverService(new InMemoryDriverRepository());
    service.addDriver(new Driver("D1", new Position(0, 0)));

    assertThrows(IllegalArgumentException.class,
        () -> service.addDriver(new Driver("D1", new Position(1, 1))));
  }

  @Test
  void fetchingUnknownDriverThrows() {
    DriverService service = new DriverService(new InMemoryDriverRepository());
    assertThrows(IllegalArgumentException.class, () -> service.getDriver("MISSING"));
  }
}

