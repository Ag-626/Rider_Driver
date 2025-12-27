package com.example.geektrust.command;

import com.example.geektrust.appservices.Services;

public final class CommandModule {

  private CommandModule(){}

  public static CommandRegistry buildRegistry(Services services) {
    return new CommandRegistry()
        .register("ADD_DRIVER", new AddDriverCommandFactory(services.driverService()))
        .register("ADD_RIDER", new AddRiderCommandFactory(services.riderService()))
        .register("MATCH", new MatchCommandFactory(services.matchService()))
        .register("START_RIDE", new StartRideCommandFactory(services.rideService()))
        .register("STOP_RIDE", new StopRideCommandFactory(services.rideService()));
  }


}
