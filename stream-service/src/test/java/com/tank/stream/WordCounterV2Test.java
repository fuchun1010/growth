package com.tank.stream;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WordCounterV2Test {

  @Test
  @SneakyThrows
  @DisplayName("批流一体单词计数")
  void count() {
    val counter = new WordCounterV2("hello", "hello");
    Assertions.assertNotNull(counter);
    counter.count();
  }
}