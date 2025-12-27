package com.example.geektrust.appservices;

import com.example.geektrust.entity.Position;
import com.example.geektrust.entity.Rider;
import com.example.geektrust.repository.InMemoryRiderRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RiderServiceTest {

  @Test
  void addAndFetchRider() {
    RiderService service = new RiderService(new InMemoryRiderRepository());
    Rider rider = new Rider("R1", new Position(2, 3));

    service.addRider(rider);
    assertEquals(rider, service.getRider("R1"));
  }

  @Test
  void addingDuplicateRiderThrows() {
    RiderService service = new RiderService(new InMemoryRiderRepository());
    service.addRider(new Rider("R1", new Position(0, 0)));

    assertThrows(IllegalArgumentException.class,
        () -> service.addRider(new Rider("R1", new Position(1, 1))));
  }

  @Test
  void fetchingUnknownRiderThrows() {
    RiderService service = new RiderService(new InMemoryRiderRepository());
    assertThrows(IllegalArgumentException.class, () -> service.getRider("MISSING"));
  }
}

