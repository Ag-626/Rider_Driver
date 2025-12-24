package com.example.geektrust.app;

import com.example.geektrust.appservices.Services;
import com.example.geektrust.cli.LineReader;
import com.example.geektrust.command.CommandModule;
import com.example.geektrust.command.CommandParser;
import com.example.geektrust.command.CommandProcessor;
import com.example.geektrust.command.CommandRegistry;
import java.io.BufferedReader;
import java.io.FileReader;

public class AppRunner {

  public void run(String inputFilePath) throws Exception{
    Services services = Services.createDefault();
    CommandRegistry registry = CommandModule.buildRegistry(services);
    CommandParser commandParser = new CommandParser();

    try(BufferedReader br = new BufferedReader(new FileReader(inputFilePath))){
      LineReader reader = new LineReader(br);
      CommandProcessor processor = new CommandProcessor(reader, commandParser, registry);
      processor.run();
    }
  }

}
