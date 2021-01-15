package com.tank.ds;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * @author tank198435163.com
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IntHeapTest {

  @Test
  void add() {
    int[] array = {3, 5, 10, 2, 7};
    IntHeap heap = accumulate(array);
    val result = heap.obtainArray();
    Assertions.assertNotNull(result);
  }

  @Test
  void add2() {
    int[] array = {1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
    IntHeap heap = accumulate(array);
    val result = heap.obtainArray();
    Assertions.assertNotNull(result);
  }

  @Test
  void add3() {
    int[] array = {3, 5, 10, 2, 7, 1};
    IntHeap heap = accumulate(array);
    val result = heap.obtainArray();
    Assertions.assertNotNull(result);
  }

  @Test
  void add4() {
    int[] array = {5, 4, 6, 9, 7, 10, 2, 8, 3};
    val heap = this.accumulate(array);
    val result = heap.obtainArray();
    Assertions.assertNotNull(result);
  }

  @Test
  void add5() {
    int[] array = {5, 4, 6, 9, 7, 2, 8, 3};
    val heap = this.accumulate(array);
    val result = heap.obtainArray();
    Assertions.assertNotNull(result);
  }

  private IntHeap accumulate(int[] array) {
    IntHeap heap = new IntHeap(array.length);
    for (int data : array) {
      heap.add(data);
    }
    return heap;
  }


}