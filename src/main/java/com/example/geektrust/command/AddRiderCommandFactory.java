package com.example.geektrust.command;

import com.example.geektrust.appservices.RiderService;
import com.example.geektrust.cli.ParsedInput;
import com.example.geektrust.entity.Position;
import com.example.geektrust.entity.Rider;
import java.util.List;
import java.util.Objects;

public class AddRiderCommandFactory implements CommandFactory{

  private final RiderService riderService;

  public AddRiderCommandFactory(RiderService riderService){
    this.riderService = riderService;
  }

  @Override
  public Command create(ParsedInput parsedInput){
    Objects.requireNonNull(parsedInput, "ParsedInput is required");

    String riderId = parsedInput.getEntityId();
    List<String> args = parsedInput.getArgs();

    if(riderId == null || riderId.isEmpty()){
      throw new IllegalArgumentException("ADD_RIDER requires a riderId");
    }
    if(args.size()<2){
      throw new IllegalArgumentException("ADD_Rider requires a x and y coordinates");
    }

    int x = Integer.parseInt(args.get(0));
    int y = Integer.parseInt(args.get(1));
    Rider rider = new Rider(riderId, new Position(x, y));
    return new AddRiderCommand(rider, riderService);
  }
}
