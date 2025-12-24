package com.example.geektrust.cli;

import java.io.BufferedReader;
import java.io.IOException;

public final class LineReader {
  private final BufferedReader br;

  public LineReader(BufferedReader br){
    this.br = br;
  }

  public  String nextLine() throws IOException {
    return br.readLine();
  }

}
