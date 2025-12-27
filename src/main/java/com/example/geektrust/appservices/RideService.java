package com.example.geektrust.appservices;

import com.example.geektrust.entity.BillResponse;
import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.MatchResult;
import com.example.geektrust.entity.Position;
import com.example.geektrust.entity.RideInfo;
import com.example.geektrust.entity.Rider;
import com.example.geektrust.repository.RideRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RideService {

  // Fare rules
  private static final BigDecimal BASE_FARE   = BigDecimal.valueOf(50.0);
  private static final BigDecimal COST_PER_KM = BigDecimal.valueOf(6.5);
  private static final BigDecimal COST_PER_MIN = BigDecimal.valueOf(2.0);
  private static final BigDecimal TAX_RATE    = BigDecimal.valueOf(0.20);

  // Rounding rules
  private static final int BILL_SCALE = 2;
  private static final RoundingMode BILL_ROUNDING_MODE = RoundingMode.HALF_UP;

  // Match constraints
  private static final int MAX_MATCHED_DRIVERS = 5;

  private final RideRepository rideRepository;
  private final MatchService matchService;
  private final RiderService riderService;
  private final DriverService driverService;

  public RideService(RideRepository rideRepository,
      MatchService matchService,
      RiderService riderService,
      DriverService driverService) {
    this.rideRepository = Objects.requireNonNull(rideRepository, "rideRepository cannot be null");
    this.matchService = Objects.requireNonNull(matchService, "matchService cannot be null");
    this.riderService = Objects.requireNonNull(riderService, "riderService cannot be null");
    this.driverService = Objects.requireNonNull(driverService, "driverService cannot be null");
  }

  // ---------------- START RIDE ----------------

  public StartRideResult startRide(String rideId, int n, String riderId) {
    if (!isValidStartInput(rideId, n, riderId)) return StartRideResult.INVALID_RIDE;
    if (rideRepository.isRideIdExist(rideId)) return StartRideResult.INVALID_RIDE;

    Optional<MatchResult> opt = matchService.findMatchResultByRiderId(riderId);
    if (!opt.isPresent()) return StartRideResult.INVALID_RIDE;

    MatchResult matchResult = opt.get();
    String driverId = pickNthDriver(matchResult.getEligibleDriverIds(), n);
    if (driverId == null) return StartRideResult.INVALID_RIDE;

    Driver driver = driverService.getDriver(driverId);
    if (driver.isUnavailable()) return StartRideResult.INVALID_RIDE;

    Rider rider = riderService.getRider(riderId);

    if (!tryStartEntities(rider, driver, rideId)) return StartRideResult.INVALID_RIDE;

    rideRepository.save(new RideInfo(rideId, driverId, riderId, rider.getPosition()));
    matchService.clearMatch(riderId);

    return StartRideResult.RIDE_STARTED;
  }

  private boolean isValidStartInput(String rideId, int n, String riderId) {
    return rideId != null && !rideId.trim().isEmpty()
        && riderId != null && !riderId.trim().isEmpty()
        && n >= 1 && n <= MAX_MATCHED_DRIVERS;
  }

  private String pickNthDriver(List<String> eligibleDriverIds, int n) {
    if (eligibleDriverIds == null) return null;
    if (eligibleDriverIds.size() < n) return null;
    return eligibleDriverIds.get(n - 1);
  }

  private boolean tryStartEntities(Rider rider, Driver driver, String rideId) {
    try {
      rider.startRide(rideId);
      driver.assignRide();
      return true;
    } catch (RuntimeException ex) {
      return false;
    }
  }

  // ---------------- STOP RIDE ----------------

  public StopRideResult stopRide(String rideId, Position destinationPosition, int timeTaken) {
    if (!isValidStopInput(rideId, destinationPosition, timeTaken)) return StopRideResult.INVALID_RIDE;

    Optional<RideInfo> opt = rideRepository.getByRideId(rideId);
    if (!opt.isPresent()) return StopRideResult.INVALID_RIDE;

    RideInfo rideInfo = opt.get();
    if (rideInfo.isRideStopped()) return StopRideResult.INVALID_RIDE;

    return performStop(rideInfo, destinationPosition, timeTaken);
  }

  private boolean isValidStopInput(String rideId, Position destinationPosition, int timeTaken) {
    return rideId != null && !rideId.trim().isEmpty()
        && destinationPosition != null
        && timeTaken >= 0;
  }

  private StopRideResult performStop(RideInfo rideInfo, Position destinationPosition, int timeTaken) {
    try {
      Rider rider = riderService.getRider(rideInfo.getRiderId());
      Driver driver = driverService.getDriver(rideInfo.getDriverId());

      // state transitions first (throws if invalid)
      rider.completeRide(rideInfo.getRideId(), destinationPosition);
      driver.completeRide(destinationPosition);

      // persist ride completion details
      rideInfo.stop(destinationPosition, timeTaken);

      return StopRideResult.RIDE_STOPPED;
    } catch (RuntimeException ex) {
      return StopRideResult.INVALID_RIDE;
    }
  }

  // ---------------- BILL ----------------

  public BillResponse billed(String rideId) {
    if (rideId == null || rideId.trim().isEmpty()) return BillResponse.invalid();

    Optional<RideInfo> opt = rideRepository.getByRideId(rideId);
    if (!opt.isPresent()) return BillResponse.invalid();

    RideInfo rideInfo = opt.get();
    if (!rideInfo.isRideStopped()) return BillResponse.notCompleted();

    BigDecimal total = calculateTotalFare(rideInfo);
    return BillResponse.billed(rideId, rideInfo.getDriverId(), total.doubleValue());
  }

  private BigDecimal calculateTotalFare(RideInfo rideInfo) {
    // Rule: all floating point values rounded to 2 decimals
    BigDecimal distanceKm = round2(BigDecimal.valueOf(rideInfo.distanceTravelled()));
    BigDecimal timeMin = BigDecimal.valueOf(rideInfo.getTimeTaken());

    BigDecimal distanceCost = round2(COST_PER_KM.multiply(distanceKm));
    BigDecimal timeCost = round2(COST_PER_MIN.multiply(timeMin));

    BigDecimal subtotal = round2(BASE_FARE.add(distanceCost).add(timeCost));
    BigDecimal tax = round2(subtotal.multiply(TAX_RATE));

    return round2(subtotal.add(tax));
  }

  private BigDecimal round2(BigDecimal value) {
    return value.setScale(BILL_SCALE, BILL_ROUNDING_MODE);
  }
}