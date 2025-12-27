package com.example.geektrust.command;

import com.example.geektrust.cli.ParsedInput;
import com.example.geektrust.entity.Position;

import java.util.List;
import java.util.Objects;

final class InputUtil {
  private InputUtil() {}

  static void requireInput(ParsedInput input) {
    Objects.requireNonNull(input, "Parsed Input is required");
  }

  static String requireEntityId(ParsedInput input, String cmdName) {
    String id = input.getEntityId();
    if (id == null || id.trim().isEmpty()) {
      throw new IllegalArgumentException(cmdName + " requires an id");
    }
    return id.trim();
  }

  static List<String> args(ParsedInput input) {
    List<String> args = input.getArgs();
    return args == null ? java.util.Collections.emptyList() : args;
  }

  static void requireArgCount(List<String> args, int required, String cmdName, String msg) {
    if (args.size() < required) {
      throw new IllegalArgumentException(cmdName + " " + msg);
    }
  }

  static int requireInt(List<String> args, int index, String cmdName, String argName) {
    if (args.size() <= index) {
      throw new IllegalArgumentException(cmdName + " requires " + argName);
    }
    String raw = args.get(index);
    try {
      return Integer.parseInt(raw);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(cmdName + " invalid " + argName + ": " + raw);
    }
  }

  static String requireStringArg(List<String> args, int index, String cmdName, String argName) {
    if (args.size() <= index) {
      throw new IllegalArgumentException(cmdName + " requires " + argName);
    }

    String value = args.get(index);
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException(cmdName + " requires " + argName);
    }

    return value.trim();
  }

  static Position requirePositionXY(List<String> args, int xIndex, int yIndex, String cmdName) {
    int x = requireInt(args, xIndex, cmdName, "X_COORDINATE");
    int y = requireInt(args, yIndex, cmdName, "Y_COORDINATE");
    return new Position(x, y);
  }
}