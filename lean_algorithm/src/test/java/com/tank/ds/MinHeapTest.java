package com.tank.ds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MinHeapTest {

  @Test
  void min() {
  }

  @Test
  void add() {
    Assertions.assertNotNull(this.minHeap);
    final Integer[] array = {8, 7, 1, 9};
    Arrays.stream(array).forEach(this.minHeap::add);
    Integer result = this.minHeap.min();
    Assertions.assertEquals(1, (int) result);
  }

  @BeforeAll
  void initialize() {
    this.minHeap = new MinHeap<>(Integer.class);
  }

  private MinHeap<Integer> minHeap;
}