package com.example.geektrust.command;

import com.example.geektrust.appservices.BillResult;
import com.example.geektrust.appservices.RideService;
import com.example.geektrust.entity.BillResponse;

public class BillCommand implements Command{

  private final String rideId;
  private final RideService rideService;

  public BillCommand(String rideId, RideService rideService){
    this.rideId = rideId;
    this.rideService = rideService;
  }

  @Override
  public void execute() {
    BillResponse res = rideService.billed(rideId);

    if (res.getBillResult() == BillResult.INVALID_RIDE) {
      System.out.println("INVALID_RIDE");
      return;
    }

    if (res.getBillResult() == BillResult.RIDE_NOT_COMPLETED) {
      System.out.println("RIDE_NOT_COMPLETED");
      return;
    }

    System.out.println(
        "BILL " + res.getRideId() + " " + res.getDriverId() + " " +
            String.format("%.2f", res.getAmount())
    );
  }

}
