package com.example.geektrust.repository;

import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryDriverRepositoryTest {

  @Test
  void saveAndFindDriver() {
    InMemoryDriverRepository repo = new InMemoryDriverRepository();
    Driver driver = new Driver("D1", new Position(1, 2));

    repo.save(driver);

    assertTrue(repo.findById("D1").isPresent());
    assertEquals(driver, repo.findById("D1").get());
  }

  @Test
  void findAllReturnsSavedDrivers() {
    InMemoryDriverRepository repo = new InMemoryDriverRepository();
    repo.save(new Driver("D1", new Position(0, 0)));
    repo.save(new Driver("D2", new Position(1, 1)));

    assertEquals(2, repo.findAll().size());
  }
}

