package com.example.geektrust.appservices;

public final class Services {

  private final DriverService driverService;
  private final RiderService riderService;
  private final RideService rideService;

  private Services (DriverService driverService, RiderService riderService, RideService rideService){
    this.driverService = driverService;
    this.riderService = riderService;
    this.rideService = rideService;
  }

  public static Services createDefault(){
    DriverService driverService = new DriverService();
    RiderService riderService = new RiderService();
    RideService rideService = new RideService();
    return new Services(driverService, riderService, rideService);
  }

}
