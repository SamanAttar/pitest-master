package com.example;

public class FailsTestWhenEnvVariableSetTestee {

  public boolean returnTrue() {
    final int i = 0;
    int j = i << 2;
    j = j + i;

      return !"true".equals(System
              .getProperty(FailsTestWhenEnvVariableSetTestee.class.getName()));
  }

}
