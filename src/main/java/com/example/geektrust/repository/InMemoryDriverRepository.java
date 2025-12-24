package com.example.geektrust.repository;

import com.example.geektrust.entity.Driver;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

public class InMemoryDriverRepository implements DriverRepository{

  private final HashMap < String, Driver > drivers = new HashMap<>();

  @Override
  public void save(Driver driver){
    drivers.put(driver.getDriverId(), driver);
  }

  @Override
  public Optional<Driver> findById(String driverId){
    return Optional.ofNullable(drivers.get(driverId));
  }

  @Override
  public Collection<Driver> findAll(){
    return drivers.values();
  }
}
