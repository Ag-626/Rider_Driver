package com.example.geektrust.command;

import com.example.geektrust.appservices.Services;
import com.example.geektrust.cli.LineReader;
import com.example.geektrust.cli.ParsedInput;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandProcessorTest {

  @Test
  void processesCommandsEndToEnd() throws Exception {
    String input = String.join("\n",
        "ADD_DRIVER D1 0 1",
        "ADD_RIDER R1 0 0",
        "MATCH R1",
        "START_RIDE ride-1 1 R1",
        "STOP_RIDE ride-1 3 4 10",
        "BILL ride-1");

    Services services = Services.createDefault();
    CommandRegistry registry = CommandModule.buildRegistry(services);
    CommandProcessor processor = new CommandProcessor(
        new LineReader(new BufferedReader(new StringReader(input))),
        new CommandParser(),
        registry);

    List<String> lines = captureOutput(() -> {
      try {
        processor.run();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    assertEquals(
        Arrays.asList("DRIVERS_MATCHED D1", "RIDE_STARTED ride-1", "RIDE_STOPPED ride-1", "BILL ride-1 D1 123.00"),
        lines);
  }

  private List<String> captureOutput(Runnable runnable) {
    PrintStream originalOut = System.out;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    System.setOut(new PrintStream(baos));
    try {
      runnable.run();
      return Arrays.stream(baos.toString().split("\\R"))
          .filter(s -> !s.trim().isEmpty())
          .collect(Collectors.toList());
    } finally {
      System.setOut(originalOut);
    }
  }
}

