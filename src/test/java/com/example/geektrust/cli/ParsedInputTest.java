package com.example.geektrust.cli;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParsedInputTest {

  @Test
  void noopHasVerbNoop() {
    ParsedInput noop = ParsedInput.noop();
    assertTrue(noop.isNoop());
  }

  @Test
  void storesVerbIdAndArgs() {
    ParsedInput parsed = new ParsedInput("CMD", "ID", Arrays.asList("A", "B"));
    assertEquals("CMD", parsed.getVerb());
    assertEquals("ID", parsed.getEntityId());
    assertEquals(2, parsed.getArgs().size());
  }
}

