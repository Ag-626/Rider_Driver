package com.example.geektrust.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PositionTest {

  @Test
  void distanceToSelfIsZero() {
    Position pos = new Position(3, 4);
    assertEquals(0.0, pos.distanceTo(pos));
  }

  @Test
  void distanceToAnotherPointIsPythagorean() {
    Position a = new Position(0, 0);
    Position b = new Position(3, 4);
    assertEquals(5.0, a.distanceTo(b));
    assertEquals(5.0, b.distanceTo(a));
  }

  @Test
  void distanceToNullThrows() {
    Position pos = new Position(1, 2);
    assertThrows(NullPointerException.class, () -> pos.distanceTo(null));
  }
}

