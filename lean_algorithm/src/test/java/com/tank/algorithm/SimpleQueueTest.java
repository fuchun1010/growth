package com.tank.algorithm;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author tank198435163.com
 */
class SimpleQueueTest {

  @Test
  @DisplayName("容积测试")
  void capacitySize() {
    this.queue = new SimpleQueue<>(6);
    val result = this.queue.capacity() & (this.queue.capacity() - 1);
    assertEquals(0, result);
  }

  @Test
  @DisplayName("入队列")
  void push() {
    this.queue.push(6);
    assertEquals(6, this.queue.size());
  }

  @Test
  @DisplayName("出队列")
  void pop() {
    assertEquals(5, this.queue.size());
    val result = this.queue.pop();
    assertEquals(1, (int) result.getOrElse(0));
  }

  @BeforeEach
  @DisplayName("初始化")
  void initialize() {
    this.queue = new SimpleQueue<>();
    IntStream.rangeClosed(1, 5).forEach(this.queue::push);
  }

  private SimpleQueue<Integer> queue;
}