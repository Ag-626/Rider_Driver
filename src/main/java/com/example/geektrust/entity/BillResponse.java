package com.example.geektrust.entity;

import com.example.geektrust.appservices.BillResult;

public class BillResponse {

  private final BillResult billResult;
  private final String driverId;
  private final String rideId;
  private final double amount;

  private BillResponse(BillResult billResult, String rideId, String driverId, double amount){
    this.billResult = billResult;
    this.driverId = driverId;
    this.rideId = rideId;
    this.amount = amount;
  }

  public static BillResponse invalid(){
    return new BillResponse(BillResult.INVALID_RIDE, null, null, 0);
  }

  public static BillResponse notCompleted(){
    return new BillResponse(BillResult.RIDE_NOT_COMPLETED, null, null, 0);
  }

  public static BillResponse billed(String rideId, String driverId, double amount){
    return new BillResponse(BillResult.BILLED, rideId, driverId, amount);
  }

  public BillResult getBillResult(){
    return this.billResult;
  }

  public String getDriverId(){
    return this.driverId;
  }

  public String getRideId(){
    return this.rideId;
  }

  public double getAmount(){
    return this.amount;
  }

}
