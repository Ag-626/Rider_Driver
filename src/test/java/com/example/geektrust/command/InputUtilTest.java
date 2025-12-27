package com.example.geektrust.command;

import com.example.geektrust.cli.ParsedInput;
import com.example.geektrust.entity.Position;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputUtilTest {

  @Test
  void requireEntityIdRejectsBlank() {
    ParsedInput input = new ParsedInput("CMD", " ", Collections.emptyList());
    assertThrows(IllegalArgumentException.class,
        () -> InputUtil.requireEntityId(input, "CMD"));
  }

  @Test
  void requireIntParsesValue() {
    assertEquals(5, InputUtil.requireInt(Collections.singletonList("5"), 0, "CMD", "ARG"));
  }

  @Test
  void requireIntRejectsInvalidNumber() {
    assertThrows(IllegalArgumentException.class,
        () -> InputUtil.requireInt(Collections.singletonList("A"), 0, "CMD", "ARG"));
  }

  @Test
  void requirePositionBuildsCoordinates() {
    Position pos = InputUtil.requirePositionXY(Arrays.asList("1", "2"), 0, 1, "CMD");
    assertEquals(1, pos.getX());
    assertEquals(2, pos.getY());
  }
}

