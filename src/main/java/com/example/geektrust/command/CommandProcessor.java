package com.example.geektrust.command;

import com.example.geektrust.cli.LineReader;
import com.example.geektrust.cli.ParsedInput;

public final class CommandProcessor {

  private final LineReader lineReader;
  private final CommandParser parser;
  private final CommandRegistry registry;

  public CommandProcessor(LineReader lineReader, CommandParser parser, CommandRegistry registry){
    this.lineReader = lineReader;
    this.parser = parser;
    this.registry = registry;
  }

  public void run() throws Exception {
    String line;
    int lineNumber = 0;

    while ((line = lineReader.nextLine())!=null){
      lineNumber++;

      ParsedInput parsedInput = parser.parse(line);

      if(parsedInput.isNoop())
        continue;

      try{
        CommandFactory factory = registry.getFactory(parsedInput.getVerb());
        Command f = factory.create(parsedInput);
        f.execute();
      }catch (Exception e){
        System.err.println("Error at line " + lineNumber + ": [" + line + "] -> " + e.getMessage());
      }
    }
  }

}
