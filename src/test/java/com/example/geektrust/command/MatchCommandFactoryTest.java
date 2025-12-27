package com.example.geektrust.command;

import com.example.geektrust.appservices.MatchService;
import com.example.geektrust.cli.ParsedInput;
import com.example.geektrust.repository.InMemoryDriverRepository;
import com.example.geektrust.repository.InMemoryMatchRepository;
import com.example.geektrust.repository.InMemoryRiderRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MatchCommandFactoryTest {

  @Test
  void createsMatchCommand() {
    MatchService matchService = new MatchService(
        new com.example.geektrust.appservices.DriverService(new InMemoryDriverRepository()),
        new com.example.geektrust.appservices.RiderService(new InMemoryRiderRepository()),
        new InMemoryMatchRepository());

    MatchCommandFactory factory = new MatchCommandFactory(matchService);
    ParsedInput input = new ParsedInput("MATCH", "R1", java.util.Collections.emptyList());

    assertNotNull(factory.create(input));
  }
}

