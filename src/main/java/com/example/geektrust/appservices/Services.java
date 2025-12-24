package com.example.geektrust.appservices;

import com.example.geektrust.repository.DriverRepository;
import com.example.geektrust.repository.InMemoryDriverRepository;
import com.example.geektrust.repository.InMemoryRiderRepository;
import com.example.geektrust.repository.RiderRepository;

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
    DriverRepository driverRepository = new InMemoryDriverRepository();
    DriverService driverService = new DriverService(driverRepository);
    RiderRepository riderRepository = new InMemoryRiderRepository();
    RiderService riderService = new RiderService(riderRepository);
    RideService rideService = new RideService();
    return new Services(driverService, riderService, rideService);
  }

  public DriverService driverService(){
    return driverService;
  }

  public RiderService riderService(){
    return riderService;
  }

}
