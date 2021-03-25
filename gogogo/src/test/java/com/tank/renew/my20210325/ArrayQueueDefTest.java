package com.tank.renew.my20210325;

import lombok.val;
import org.junit.jupiter.api.*;

import java.util.stream.IntStream;

/**
 * @author tank198435163.com
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("数组栈测试")
class ArrayQueueDefTest {

  @Test
  @DisplayName("出栈")
  void deQueue() {
    IntStream.rangeClosed(1, 10).boxed().forEach(this.queueDef::enQueue);
    IntStream.rangeClosed(1, 10).boxed().forEach(index -> {
      val num = this.queueDef.deQueue();
      Assertions.assertTrue(num >= 1);
    });
  }

  @Test
  @DisplayName("入栈")
  void enQueue() {
    IntStream.rangeClosed(1, 10).boxed().forEach(this.queueDef::enQueue);
    Assertions.assertEquals(this.queueDef.size(), 10);
  }

  @BeforeEach
  void initialize() {
    this.queueDef = new ArrayQueueDef<>(Integer.class);
  }

  private ArrayQueueDef<Integer> queueDef = null;
}