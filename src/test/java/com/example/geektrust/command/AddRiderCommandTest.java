package com.example.geektrust.command;

import com.example.geektrust.appservices.RiderService;
import com.example.geektrust.entity.Position;
import com.example.geektrust.entity.Rider;
import com.example.geektrust.repository.InMemoryRiderRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddRiderCommandTest {

  @Test
  void executesAddRider() {
    RiderService service = new RiderService(new InMemoryRiderRepository());
    Rider rider = new Rider("R1", new Position(2, 2));

    new AddRiderCommand(rider, service).execute();

    assertEquals(rider, service.getRider("R1"));
  }
}

