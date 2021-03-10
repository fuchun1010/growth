package com.tank.stream.udf.demo;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WordForUdfTest {

  @Test
  @DisplayName("测试udf")
  void statistics() {
    Assertions.assertNotNull(this.wordForUdf);
    this.wordForUdf.statistics("hello", "jack", "hello", "welcome", "china");
  }

  @BeforeEach
  void init() {
    this.wordForUdf = new WordForUdf();
  }

  private WordForUdf wordForUdf;
}