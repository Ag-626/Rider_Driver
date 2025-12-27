package com.example.geektrust.entity;

import com.example.geektrust.appservices.BillResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BillResponseTest {

  @Test
  void invalidResponseHasNoIdentifiersAndZeroAmount() {
    BillResponse response = BillResponse.invalid();
    assertEquals(BillResult.INVALID_RIDE, response.getBillResult());
    assertNull(response.getRideId());
    assertNull(response.getDriverId());
    assertEquals(0.0, response.getAmount());
  }

  @Test
  void billedResponseCarriesDetails() {
    BillResponse response = BillResponse.billed("ride-1", "D1", 123.45);

    assertEquals(BillResult.BILLED, response.getBillResult());
    assertEquals("ride-1", response.getRideId());
    assertEquals("D1", response.getDriverId());
    assertEquals(123.45, response.getAmount());
  }
}

