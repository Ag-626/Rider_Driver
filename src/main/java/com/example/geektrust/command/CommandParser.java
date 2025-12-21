package com.example.geektrust.command;

import com.example.geektrust.cli.ParsedInput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CommandParser {
  public ParsedInput parser (String line) {
    String trimmed = (line == null) ? "" : line.trim();
    if (trimmed.isEmpty())
      return ParsedInput.noop();

    String[] parts = trimmed.split("\\s+");
    Iterator<String> it = Arrays.asList(parts).iterator();

    String verb = it.hasNext() ? it.next() : null;

    if(!(it.hasNext()))
      return ParsedInput.noop();

    String id = it.next();
    List<String> args = new ArrayList<>();

    while(it.hasNext()){
      args.add(it.next());
    }
    return new ParsedInput(verb, id, args);
  }
}
