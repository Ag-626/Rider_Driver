package com.example.geektrust.repository;

import com.example.geektrust.entity.Driver;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public interface DriverRepository {

  void save(Driver driver);
  Optional<Driver> findById(String driverId);
  Collection<Driver> findAll();

}
