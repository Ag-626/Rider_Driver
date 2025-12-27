package com.example.geektrust.appservices;

import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.MatchResult;
import com.example.geektrust.entity.Position;
import com.example.geektrust.entity.RideInfo;
import com.example.geektrust.entity.Rider;
import com.example.geektrust.repository.RideRepository;
import java.util.List;
import java.util.Optional;

public class RideService {

  private final RideRepository rideRepository;
  private final MatchService matchService;
  private final RiderService riderService;
  private final DriverService driverService;

  public RideService(RideRepository rideRepository, MatchService matchService, RiderService riderService, DriverService driverService){
    this.rideRepository = rideRepository;
    this.matchService = matchService;
    this.riderService = riderService;
    this.driverService = driverService;
  }

  public StartRideResult startRide(String rideId, int n, String riderId) {

    if (rideId == null || rideId.trim().isEmpty()) return StartRideResult.INVALID_RIDE;
    if (riderId == null || riderId.trim().isEmpty()) return StartRideResult.INVALID_RIDE;
    if (n < 1 || n > 5) return StartRideResult.INVALID_RIDE;
    if (rideRepository.isRideIdExist(rideId)) return StartRideResult.INVALID_RIDE;

    Optional<MatchResult> opt = matchService.findMatchResultByRiderId(riderId);
    if (!opt.isPresent()) return StartRideResult.INVALID_RIDE;

    MatchResult matchResult = opt.get();
    List<String> ids = matchResult.getEligibleDriverIds();
    if (ids.size() < n) return StartRideResult.INVALID_RIDE;

    String driverId = ids.get(n - 1);

    Driver driver = driverService.getDriver(driverId);
    if (driver.isUnavailable()) return StartRideResult.INVALID_RIDE;

    Rider rider = riderService.getRider(riderId);

    try {
      rider.startRide(rideId);
      driver.assignRide();
    } catch (RuntimeException ex) {
      return StartRideResult.INVALID_RIDE;
    }

    rideRepository.save(new RideInfo(rideId, driverId, riderId, rider.getPosition()));
    matchService.clearMatch(riderId);

    return StartRideResult.RIDE_STARTED;
  }

  public StopRideResult stopRide(String rideId, Position destinationPosition, int timeTaken){
    if(rideId == null || rideId.trim().isEmpty()) return StopRideResult.INVALID_RIDE;

    if(destinationPosition == null) return StopRideResult.INVALID_RIDE;

    if(!(rideRepository.isRideIdExist(rideId))) return StopRideResult.INVALID_RIDE;

    Optional<RideInfo> ride = rideRepository.getByRideId(rideId);

    if(!(ride.isPresent()))
      return StopRideResult.INVALID_RIDE;
    RideInfo rideInfo = ride.get();
    if(rideInfo.isRideStopped())
      return StopRideResult.INVALID_RIDE;

    String riderId = rideInfo.getRiderId();
    Rider rider = riderService.getRider(riderId);

    String driverId = rideInfo.getDriverId();
    Driver driver = driverService.getDriver(driverId);

    try{
      rider.completeRide(rideId, destinationPosition);
      driver.completeRide(destinationPosition);
      rideInfo.stop(destinationPosition, timeTaken);
    }catch (Exception e){
      return StopRideResult.INVALID_RIDE;
    }

    return StopRideResult.RIDE_STOPPED;
  }

}
