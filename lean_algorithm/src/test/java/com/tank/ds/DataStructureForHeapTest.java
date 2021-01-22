package com.tank.ds;

import io.vavr.collection.Stream;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * @author tank198435163.com
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DataStructureForHeapTest {

  @Test
  void testMaxHeap1() {
    final Integer[] array = {5, 9, 19, 7, 8};
    for (val data : array) {
      this.heap.add(data);
    }
    Assertions.assertEquals(19, (int) this.heap.obtain());
  }

  @Test
  void testMaxHeap2() {
    final Integer[] array = {5, 7, 8, 4, 3, 9};
    Stream.of(array).forEach(this.heap::add);
    Assertions.assertEquals(9, (int) this.heap.obtain());
  }

  @Test
  void testRemove() {
    final Integer[] array = {5, 9, 19, 7, 8};
    for (val data : array) {
      this.heap.add(data);
    }
    this.heap.remove();
    Assertions.assertNotNull(this.heap);
    Assertions.assertEquals(9, (int) this.heap.obtain());
  }


  @BeforeAll
  void initialize() {
    this.heap = new DataStructureForHeap<>(Integer.class);
  }

  private DataStructureForHeap<Integer> heap;
}