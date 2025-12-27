package com.example.geektrust.command;

import com.example.geektrust.appservices.DriverService;
import com.example.geektrust.entity.Driver;

import java.util.Objects;

public class AddDriverCommand implements Command {

  private final Driver driver;
  private final DriverService driverService;

  public AddDriverCommand(Driver driver, DriverService driverService) {
    this.driver = Objects.requireNonNull(driver, "driver is required");
    this.driverService = Objects.requireNonNull(driverService, "driverService is required");
  }

  @Override
  public void execute() {
    driverService.addDriver(driver);
  }
}