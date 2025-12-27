package com.example.geektrust.cli;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LineReaderTest {

  @Test
  void readsLinesSequentially() throws Exception {
    LineReader reader = new LineReader(new BufferedReader(new StringReader("one\ntwo")));

    assertEquals("one", reader.nextLine());
    assertEquals("two", reader.nextLine());
    assertNull(reader.nextLine());
  }
}

