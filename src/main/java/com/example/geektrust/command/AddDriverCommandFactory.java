package com.example.geektrust.command;

import com.example.geektrust.appservices.DriverService;
import com.example.geektrust.cli.ParsedInput;
import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.Position;
import java.util.List;
import java.util.Objects;

public class AddDriverCommandFactory implements CommandFactory{

  private final DriverService driverService;

  public AddDriverCommandFactory(DriverService driverService){
    this.driverService = driverService;
  }

  @Override
  public Command create(ParsedInput parsedInput){
    Objects.requireNonNull(parsedInput, "Parsed Input is required");
    String driverId = parsedInput.getEntityId();
    List <String> args = parsedInput.getArgs();

    if(driverId == null || driverId.isEmpty()){
      throw new IllegalArgumentException("ADD_Driver requires a driverId");
    }
    if(parsedInput.getArgs().size()<2){
      throw new IllegalArgumentException("ADD_Driver requires a x and y coordinates");
    }

    Driver driver = new Driver(driverId, new Position(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1))));
    return new AddDriverCommand(driver, driverService);
  }

}
