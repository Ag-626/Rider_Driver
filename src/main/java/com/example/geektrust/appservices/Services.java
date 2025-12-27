package com.example.geektrust.appservices;

import com.example.geektrust.repository.DriverRepository;
import com.example.geektrust.repository.InMemoryDriverRepository;
import com.example.geektrust.repository.InMemoryMatchRepository;
import com.example.geektrust.repository.InMemoryRideRepository;
import com.example.geektrust.repository.InMemoryRiderRepository;
import com.example.geektrust.repository.MatchRepository;
import com.example.geektrust.repository.RideRepository;
import com.example.geektrust.repository.RiderRepository;

public final class Services {

  private final DriverService driverService;
  private final RiderService riderService;
  private final MatchService matchService;
  private final RideService rideService;

  private Services (DriverService driverService, RiderService riderService, MatchService matchService, RideService rideService){
    this.driverService = driverService;
    this.riderService = riderService;
    this.matchService = matchService;
    this.rideService = rideService;
  }

  public static Services createDefault(){
    DriverRepository driverRepository = new InMemoryDriverRepository();
    DriverService driverService = new DriverService(driverRepository);
    RiderRepository riderRepository = new InMemoryRiderRepository();
    RiderService riderService = new RiderService(riderRepository);
    MatchRepository matchRepository = new InMemoryMatchRepository();
    MatchService matchService = new MatchService(driverService, riderService, matchRepository);
    RideRepository rideRepository = new InMemoryRideRepository();
    RideService rideService = new RideService(rideRepository, matchService, riderService, driverService);
    return new Services(driverService, riderService, matchService, rideService);
  }

  public DriverService driverService(){
    return driverService;
  }

  public RiderService riderService(){
    return riderService;
  }

  public MatchService matchService(){
    return matchService;
  }

  public RideService rideService(){
    return rideService;
  }

}
