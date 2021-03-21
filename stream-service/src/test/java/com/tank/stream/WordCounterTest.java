package com.tank.stream;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WordCounterTest {

  @Test
  @DisplayName("批处理单词计数")
  void count() {
    val wordCounter = new WordCounter("hello", "hello", "jack", "peter", "hello");
    Assertions.assertNotNull(wordCounter);
    wordCounter.count();
  }

  @Test
  @DisplayName("单词流")
  void wordsStream() {
  }

}