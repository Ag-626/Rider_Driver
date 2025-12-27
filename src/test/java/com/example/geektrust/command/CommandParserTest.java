package com.example.geektrust.command;

import com.example.geektrust.cli.ParsedInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandParserTest {

  private final CommandParser parser = new CommandParser();

  @Test
  void emptyLineReturnsNoop() {
    ParsedInput parsed = parser.parse("   ");
    assertTrue(parsed.isNoop());
  }

  @Test
  void parsesVerbIdAndArgs() {
    ParsedInput parsed = parser.parse("CMD id arg1 arg2");

    assertEquals("CMD", parsed.getVerb());
    assertEquals("id", parsed.getEntityId());
    assertEquals(2, parsed.getArgs().size());
  }

  @Test
  void lineWithoutIdIsNoop() {
    ParsedInput parsed = parser.parse("CMD");
    assertTrue(parsed.isNoop());
  }
}

