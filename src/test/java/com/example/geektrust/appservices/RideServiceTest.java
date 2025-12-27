package com.example.geektrust.appservices;

import com.example.geektrust.entity.BillResponse;
import com.example.geektrust.appservices.BillResult;
import com.example.geektrust.appservices.StartRideResult;
import com.example.geektrust.appservices.StopRideResult;
import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.Position;
import com.example.geektrust.entity.Rider;
import com.example.geektrust.repository.InMemoryDriverRepository;
import com.example.geektrust.repository.InMemoryMatchRepository;
import com.example.geektrust.repository.InMemoryRideRepository;
import com.example.geektrust.repository.InMemoryRiderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RideServiceTest {

  private RideService rideService;
  private MatchService matchService;
  private RiderService riderService;
  private DriverService driverService;

  @BeforeEach
  void setUp() {
    driverService = new DriverService(new InMemoryDriverRepository());
    riderService = new RiderService(new InMemoryRiderRepository());
    matchService = new MatchService(driverService, riderService, new InMemoryMatchRepository());
    rideService = new RideService(new InMemoryRideRepository(), matchService, riderService, driverService);
  }

  @Test
  void startStopAndBillHappyPath() {
    driverService.addDriver(new Driver("D1", new Position(0, 1)));
    riderService.addRider(new Rider("R1", new Position(0, 0)));
    matchService.match("R1");

    assertSame(StartRideResult.RIDE_STARTED, rideService.startRide("ride-1", 1, "R1"));

    assertSame(StopRideResult.RIDE_STOPPED,
        rideService.stopRide("ride-1", new Position(3, 4), 10));

    BillResponse bill = rideService.billed("ride-1");
    assertSame(BillResult.BILLED, bill.getBillResult());
    assertEquals("D1", bill.getDriverId());
    assertEquals(123.00, bill.getAmount());
  }

  @Test
  void startRideFailsWithInvalidInput() {
    assertSame(StartRideResult.INVALID_RIDE, rideService.startRide(null, 1, "R1"));
  }

  @Test
  void billBeforeRideCompletesReturnsNotCompleted() {
    driverService.addDriver(new Driver("D1", new Position(0, 0)));
    riderService.addRider(new Rider("R1", new Position(0, 0)));
    matchService.match("R1");
    rideService.startRide("ride-1", 1, "R1");

    BillResponse response = rideService.billed("ride-1");
    assertSame(BillResult.RIDE_NOT_COMPLETED, response.getBillResult());
  }

  @Test
  void stopRideForUnknownRideReturnsInvalid() {
    assertSame(StopRideResult.INVALID_RIDE,
        rideService.stopRide("unknown", new Position(0, 0), 5));
  }

  @Test
  void matchClearedAfterSuccessfulStart() {
    driverService.addDriver(new Driver("D1", new Position(0, 0)));
    riderService.addRider(new Rider("R1", new Position(0, 0)));
    matchService.match("R1");

    rideService.startRide("ride-1", 1, "R1");

    assertThrows(java.util.NoSuchElementException.class,
        () -> matchService.findMatchResultByRiderId("R1").get());
  }
}

