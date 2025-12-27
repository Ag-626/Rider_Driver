package com.example.geektrust.repository;

import com.example.geektrust.entity.RideInfo;
import java.util.Optional;

public interface RideRepository {

  void save(RideInfo rideInfo);
  boolean isRideIdExist(String rideId);
  Optional<RideInfo> getByRideId(String rideId);

}
