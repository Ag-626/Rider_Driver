package com.example.geektrust.command;

import com.example.geektrust.appservices.RiderService;
import com.example.geektrust.cli.ParsedInput;
import com.example.geektrust.repository.InMemoryRiderRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddRiderCommandFactoryTest {

  @Test
  void createsAddRiderCommand() {
    AddRiderCommandFactory factory = new AddRiderCommandFactory(
        new RiderService(new InMemoryRiderRepository()));
    ParsedInput input = new ParsedInput("ADD_RIDER", "R1", Arrays.asList("2", "3"));

    Command command = factory.create(input);
    assertNotNull(command);
  }

  @Test
  void missingArgsThrows() {
    AddRiderCommandFactory factory = new AddRiderCommandFactory(
        new RiderService(new InMemoryRiderRepository()));
    ParsedInput input = new ParsedInput("ADD_RIDER", "R1", Arrays.asList("1"));

    assertThrows(IllegalArgumentException.class, () -> factory.create(input));
  }
}

