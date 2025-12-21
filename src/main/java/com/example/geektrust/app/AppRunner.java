package com.example.geektrust.app;

import com.example.geektrust.appservices.Services;
import com.example.geektrust.command.CommandModule;
import com.example.geektrust.command.CommandRegistry;

public class AppRunner {

  public void run(String inputFilePath) throws Exception{
    Services services = Services.createDefault();
    CommandRegistry registry = CommandModule.buildRegistry(services);
  }

}
