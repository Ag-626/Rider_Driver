package com.example.geektrust.command;

import com.example.geektrust.appservices.RiderService;
import com.example.geektrust.entity.Rider;

import java.util.Objects;

public class AddRiderCommand implements Command {

  private final Rider rider;
  private final RiderService riderService;

  public AddRiderCommand(Rider rider, RiderService riderService) {
    this.rider = Objects.requireNonNull(rider, "rider is required");
    this.riderService = Objects.requireNonNull(riderService, "riderService is required");
  }

  @Override
  public void execute() {
    riderService.addRider(rider);
  }
}