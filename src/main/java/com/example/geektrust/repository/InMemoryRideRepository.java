package com.example.geektrust.repository;

import com.example.geektrust.entity.RideInfo;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class InMemoryRideRepository implements RideRepository {

  private final Map<String, RideInfo> rides = new HashMap<>();

  @Override
  public void save(RideInfo rideInfo){
    Objects.requireNonNull(rideInfo, "Ride Info cannot be null");
    rides.put(rideInfo.getRideId(), rideInfo);
  }

  @Override
  public boolean isRideIdExist(String rideId){
    Objects.requireNonNull(rideId, "Ride Id cannot be null");
    return rides.containsKey(rideId);
  }

  @Override
  public Optional<RideInfo> getByRideId(String rideId){
    Objects.requireNonNull(rideId, "Ride Id cannot be null");
    return Optional.ofNullable(rides.get(rideId));
  }



}
