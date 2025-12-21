package com.example.geektrust.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class CommandRegistry {
  Map< String, CommandFactory > factories = new HashMap<>();

  CommandRegistry (){}

  CommandRegistry register(String verb, CommandFactory factory){
    Objects.requireNonNull(verb, "Verb can't be null");
    Objects.requireNonNull(factory, "Factory can't be null");

    if(factories.containsKey(verb)){
      throw new IllegalStateException("Duplicate registration for verb: " + verb);
    }
    factories.put(verb, factory);
    return this;
  }

  public CommandFactory getFactory(String verb){
    Objects.requireNonNull(verb, "Verb can't be null if you to get the factory");
    CommandFactory factory = factories.get(verb);
    if(factory != null)
      return factory;
    throw new IllegalArgumentException("Unknown command verb: " + verb);
  }

}
