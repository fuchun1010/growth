package com.tank.ds;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MinHeapTest {

  @Test
  void min() {
    Assertions.assertNotNull(this.minHeap);
    final Integer[] array = {8, 7, 1, 9};
    Arrays.stream(array).forEach(this.minHeap::add);
    Integer result = this.minHeap.min();
    Assertions.assertEquals(1, (int) result);
  }

  @Test
  void add() {
    Assertions.assertNotNull(this.minHeap);
    final Integer[] array = {8, 7, 1, 9};
    Arrays.stream(array).forEach(this.minHeap::add);
    Integer result = this.minHeap.min();
    Assertions.assertEquals(1, (int) result);
  }

  @Test
  void add1() {
    final Integer[] array = {13, 11, 12, 26, 9, 24, 30, 28, 3, 7, 6, 29, 17, 18, 16, 10};
    Arrays.stream(array).forEach(this.minHeap::add);
    val result = this.minHeap.min();
    final Integer[] fullArray = this.minHeap.obtain();
    Assertions.assertEquals(3, (int) result);
    Assertions.assertEquals(6, (int) fullArray[1]);
    //Assertions.assertEquals();
  }

  @Test
  void buildHeap() {
    final Integer[] array = {13, 11, 12, 26, 9, 24, 30, 28, 3, 7, 6, 29, 17, 18, 16, 10};
    this.minHeap = new MinHeap<>(array);
    for (int index = 1; index < array.length; index++) {
      this.minHeap.siftUp(index);
    }
    val result = this.minHeap.min();
    Assertions.assertEquals(3, (int) result);
  }

  @Test
  void testArrayCopy() {
    final Integer[] array = {13, 11, 12, 26};
    final Integer[] newArray = new Integer[array.length << 1];
    System.arraycopy(array, 0, newArray, 0, array.length);
    Assertions.assertNotNull(array);
    Assertions.assertNotNull(newArray);
    Assertions.assertNull(newArray[array.length]);
  }


  @BeforeEach
  void initialize() {
    this.minHeap = new MinHeap<>(Integer.class);
  }

  private MinHeap<Integer> minHeap;
}