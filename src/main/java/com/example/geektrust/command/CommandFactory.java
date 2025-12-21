package com.example.geektrust.command;

import com.example.geektrust.cli.ParsedInput;

public interface CommandFactory {
  Command create(ParsedInput input);
}
