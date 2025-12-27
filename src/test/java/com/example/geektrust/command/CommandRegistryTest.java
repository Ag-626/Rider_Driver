package com.example.geektrust.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandRegistryTest {

  @Test
  void registersAndRetrievesFactory() {
    CommandRegistry registry = new CommandRegistry();
    registry.register("TEST", input -> () -> {});

    assertNotNull(registry.getFactory("TEST"));
  }

  @Test
  void duplicateRegistrationFails() {
    CommandRegistry registry = new CommandRegistry();
    registry.register("TEST", input -> () -> {});

    assertThrows(IllegalStateException.class,
        () -> registry.register("TEST", input -> () -> {}));
  }

  @Test
  void unknownVerbThrows() {
    CommandRegistry registry = new CommandRegistry();
    assertThrows(IllegalArgumentException.class, () -> registry.getFactory("MISSING"));
  }
}

