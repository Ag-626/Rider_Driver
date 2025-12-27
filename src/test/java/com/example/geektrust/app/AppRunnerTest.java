package com.example.geektrust.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppRunnerTest {

  @TempDir
  Path tempDir;

  @Test
  void runsCommandsFromFile() throws Exception {
    Path input = tempDir.resolve("input.txt");
    writeLines(input, Arrays.asList(
        "ADD_DRIVER D1 0 1",
        "ADD_RIDER R1 0 0",
        "MATCH R1",
        "START_RIDE ride-1 1 R1",
        "STOP_RIDE ride-1 3 4 10",
        "BILL ride-1"
    ));

    List<String> output = captureOutput(() -> {
      try {
        new AppRunner().run(input.toString());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    assertEquals(
        Arrays.asList("DRIVERS_MATCHED D1", "RIDE_STARTED ride-1", "RIDE_STOPPED ride-1", "BILL ride-1 D1 123.00"),
        output);
  }

  private void writeLines(Path file, List<String> lines) throws IOException {
    try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(file))) {
      lines.forEach(writer::println);
    }
  }

  private List<String> captureOutput(Runnable runnable) {
    java.io.PrintStream original = System.out;
    java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
    System.setOut(new java.io.PrintStream(baos));
    try {
      runnable.run();
      return Arrays.stream(baos.toString().split("\\R"))
          .filter(s -> !s.trim().isEmpty())
          .collect(Collectors.toList());
    } finally {
      System.setOut(original);
    }
  }
}

