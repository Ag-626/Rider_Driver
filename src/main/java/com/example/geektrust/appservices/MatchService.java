package com.example.geektrust.appservices;

import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.MatchResult;
import com.example.geektrust.entity.Position;
import com.example.geektrust.entity.Rider;
import com.example.geektrust.repository.MatchRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MatchService {

  private static final double DEFAULT_MAX_DISTANCE = 5.0;

  private final DriverService driverService;
  private final RiderService riderService;
  private final MatchRepository matchRepository;

  public MatchService(DriverService driverService,
      RiderService riderService,
      MatchRepository matchRepository) {
    this.driverService = Objects.requireNonNull(driverService, "driverService cannot be null");
    this.riderService = Objects.requireNonNull(riderService, "riderService cannot be null");
    this.matchRepository = Objects.requireNonNull(matchRepository, "matchRepository cannot be null");
  }

  public MatchResult match(String riderId) {
    Objects.requireNonNull(riderId, "riderId cannot be null");

    Rider rider = riderService.getRider(riderId);

    // Throws if ON_RIDE, otherwise sets LOOKING_FOR_RIDE (refresh allowed)
    rider.requestRide();

    Position riderPos = rider.getPosition();
    List<Driver> eligibleDrivers = findEligibleDrivers(riderPos, DEFAULT_MAX_DISTANCE);

    List<String> eligibleDriverIds = new ArrayList<>();
    for (Driver d : eligibleDrivers) {
      eligibleDriverIds.add(d.getDriverId());
    }

    MatchResult result = new MatchResult(riderId, eligibleDriverIds);

    // Save even if empty — consistent state for START_RIDE
    matchRepository.save(result);

    return result;
  }

  private List<Driver> findEligibleDrivers(Position riderPosition, double maxDistance) {
    List<Driver> eligibleDrivers = new ArrayList<>();

    for (Driver d : driverService.getAllDrivers()) {
      if (!d.isAvailable()) continue;

      double dist = d.getPosition().distanceTo(riderPosition);
      if (dist <= maxDistance) eligibleDrivers.add(d);
    }

    eligibleDrivers.sort(
        Comparator.comparingDouble((Driver d) -> d.getPosition().distanceTo(riderPosition))
            .thenComparing(Driver::getDriverId)
    );

    return eligibleDrivers;
  }
}