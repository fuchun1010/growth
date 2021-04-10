package com.tank.stream.my20210409;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WordStatisticTest {

  @Test
  @DisplayName("流统计")
  void statisticsWithStream() {
    Assertions.assertNotNull(this.stream);
    this.stream.statistics();
  }

  @Test
  @DisplayName("批处理")
  void statisticsWithBatch() {

  }

  @BeforeEach
  void initialization() {
    this.stream = new WordStatisticStream();
  }

  private WordStatistic stream;
}