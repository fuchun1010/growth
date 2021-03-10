package com.tank.stream.udf.demo;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CounterForUdfTest {

  @Test
  @DisplayName("聚合udf测试")
  void statistics() {
    Assertions.assertNotNull(this.counterForUdf);
    this.counterForUdf.statistics("hello", "hello");
  }

  @BeforeEach
  void init() {
    this.counterForUdf = new CounterForUdf();
  }

  private CounterForUdf counterForUdf;
}