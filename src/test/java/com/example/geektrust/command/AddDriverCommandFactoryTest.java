package com.example.geektrust.command;

import com.example.geektrust.appservices.DriverService;
import com.example.geektrust.cli.ParsedInput;
import com.example.geektrust.repository.InMemoryDriverRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddDriverCommandFactoryTest {

  @Test
  void createsAddDriverCommand() {
    AddDriverCommandFactory factory = new AddDriverCommandFactory(
        new DriverService(new InMemoryDriverRepository()));
    ParsedInput input = new ParsedInput("ADD_DRIVER", "D1", Arrays.asList("0", "1"));

    Command command = factory.create(input);
    assertNotNull(command);
  }

  @Test
  void missingArgsThrows() {
    AddDriverCommandFactory factory = new AddDriverCommandFactory(
        new DriverService(new InMemoryDriverRepository()));
    ParsedInput input = new ParsedInput("ADD_DRIVER", "D1", Arrays.asList("0"));

    assertThrows(IllegalArgumentException.class, () -> factory.create(input));
  }
}

