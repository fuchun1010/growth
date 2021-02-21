package com.tank.stream;

import lombok.val;

/**
 * @author tank198435163.com
 */
public class App {
  public static void main(final String[] args) {
    val counter = new WordCounterV2("hello", "hello", "jack");
    counter.count();
  }
}
