package com.example.geektrust.cli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ParsedInput {
  private final String verb;
  private final String entityId;
  private final List<String> args;

  public ParsedInput(String verb, String entityId, List<String> args){
    this.verb = Objects.requireNonNull(verb, "verb can't be negative");
    this.entityId = entityId;
    this.args = Collections.unmodifiableList(new ArrayList<>(args));
  }

  public static ParsedInput noop(){
    return new ParsedInput("NOOP", null, Collections.<String>emptyList());
  }

  public boolean isNoop(){
    return "NOOP".equals(verb);
  }

  public String getEntityId() {
    return entityId;
  }

  public String getVerb() {
    return verb;
  }

  public List<String> getArgs() {
    return args;
  }

}
