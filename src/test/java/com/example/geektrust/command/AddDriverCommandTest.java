package com.example.geektrust.command;

import com.example.geektrust.appservices.DriverService;
import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.Position;
import com.example.geektrust.repository.InMemoryDriverRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddDriverCommandTest {

  @Test
  void executesAddDriver() {
    DriverService service = new DriverService(new InMemoryDriverRepository());
    Driver driver = new Driver("D1", new Position(1, 1));

    new AddDriverCommand(driver, service).execute();

    assertEquals(driver, service.getDriver("D1"));
  }
}

