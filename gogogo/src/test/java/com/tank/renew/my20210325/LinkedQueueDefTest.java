package com.tank.renew.my20210325;

import lombok.val;
import org.junit.jupiter.api.*;

import java.util.stream.IntStream;

/**
 * @author tank198435163.com
 */
@DisplayName("双向链表实现队列")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LinkedQueueDefTest {

  @Test
  @DisplayName("出队")
  void deQueue() {
    IntStream.rangeClosed(1, 10).boxed().forEach(this.queueDef::enQueue);
    while (true) {
      val result = this.queueDef.deQueue();
      if (result != null) {
        Assertions.assertTrue(result > 0);
        System.out.println("result -> " + result);
      } else {
        break;
      }
    }
    Assertions.assertEquals(this.queueDef.size(), 0);
  }

  @Test
  @DisplayName("入队")
  void enQueue() {
    IntStream.rangeClosed(1, 10).boxed().forEach(this.queueDef::enQueue);
    Assertions.assertEquals(this.queueDef.size(), 10);
  }

  @Test
  void size() {
    IntStream.rangeClosed(1, 10).boxed().forEach(this.queueDef::enQueue);
    Assertions.assertEquals(this.queueDef.size(), 10);
  }

  @BeforeEach
  void initialize() {
    this.queueDef = new LinkedQueueDef<>();
  }

  private QueueDef<Integer> queueDef;
}