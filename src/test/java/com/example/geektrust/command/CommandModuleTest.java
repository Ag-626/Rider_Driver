package com.example.geektrust.command;

import com.example.geektrust.appservices.Services;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommandModuleTest {

  @Test
  void buildRegistryRegistersAllCommands() {
    CommandRegistry registry = CommandModule.buildRegistry(Services.createDefault());

    assertNotNull(registry.getFactory("ADD_DRIVER"));
    assertNotNull(registry.getFactory("ADD_RIDER"));
    assertNotNull(registry.getFactory("MATCH"));
    assertNotNull(registry.getFactory("START_RIDE"));
    assertNotNull(registry.getFactory("STOP_RIDE"));
    assertNotNull(registry.getFactory("BILL"));
  }
}

