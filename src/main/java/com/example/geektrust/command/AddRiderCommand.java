package com.example.geektrust.command;

import com.example.geektrust.appservices.DriverService;
import com.example.geektrust.appservices.RiderService;
import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.Rider;
import com.example.geektrust.repository.DriverRepository;

public class AddRiderCommand implements Command{

  private final Rider rider;
  private final RiderService riderService;

  public AddRiderCommand(Rider rider, RiderService riderService){
    this.rider = rider;
    this.riderService = riderService;
  }

  @Override
  public void execute(){
    riderService.addRider(rider);
  }

}
