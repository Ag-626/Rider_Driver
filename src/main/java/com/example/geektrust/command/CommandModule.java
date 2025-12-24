package com.example.geektrust.command;

import com.example.geektrust.appservices.Services;

public final class CommandModule {

  private CommandModule(){}

  public static CommandRegistry buildRegistry(Services services){
    CommandRegistry registry = new CommandRegistry();

    registry.register("ADD_DRIVER", new AddDriverCommandFactory(services.driverService()));
    return registry;
  }


}
