package com.example.geektrust.appservices;

import com.example.geektrust.entity.Driver;
import com.example.geektrust.repository.DriverRepository;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class DriverService {

  private final DriverRepository driverRepository;

  public DriverService(DriverRepository driverRepository){
    this.driverRepository = Objects.requireNonNull(driverRepository, "Driver Repository should not be null");
  }

  public void addDriver(Driver driver){
    Objects.requireNonNull(driver, "Driver must not be null");

    if(driverRepository.findById(driver.getDriverId()).isPresent()){
      throw new IllegalArgumentException("Driver already exist: " + driver.getDriverId());
    }
    driverRepository.save(driver);
  }

  public Driver getDriver(String driverId){
    return (driverRepository.findById(driverId).orElseThrow(() -> new IllegalArgumentException("Driver not found: "+ driverId)));
  }

  public Collection<Driver> getAllDrivers(){
    return driverRepository.findAll();
  }

}
