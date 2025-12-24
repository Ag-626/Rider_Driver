package com.example.geektrust.command;

import com.example.geektrust.appservices.DriverService;
import com.example.geektrust.entity.Driver;

public class AddDriverCommand implements Command{

  private final Driver driver;
  private final DriverService driverService;

  public AddDriverCommand (Driver driver, DriverService driverService){
    this.driver = driver;
    this.driverService = driverService;
  }
  @Override
  public void execute(){
    this.driverService.addDriver(this.driver);
  }

}
