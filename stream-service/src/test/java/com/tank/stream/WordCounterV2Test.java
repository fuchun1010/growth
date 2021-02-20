package com.tank.stream;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WordCounterV2Test {

  @Test
  @SneakyThrows
  void count() {
    val counter = new WordCounterV2("hello", "hello");
    counter.count();
  }
}