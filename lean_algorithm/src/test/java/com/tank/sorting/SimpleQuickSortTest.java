package com.tank.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SimpleQuickSortTest {

  @Test
  void sort() {
    final Integer[] source = {5, 1, 6, 3};
    this.simpleQuickSort.sort(source, 0, source.length - 1);
    System.out.println();
  }

  @Test
  void partition() {
    final Integer[] source = {5, 1, 6, 3};
    int index = this.simpleQuickSort.partition(source, 0, source.length);
    Assertions.assertTrue(index > 0);
  }

  @Test
  void partition2() {
    final Integer[] source = {3, 1, 5};
    int index = this.simpleQuickSort.partition(source, 0, source.length);
    Assertions.assertTrue(index > 0);
  }

  @BeforeEach
  void initialize() {
    this.simpleQuickSort = new SimpleQuickSort();
  }

  private SimpleQuickSort simpleQuickSort;
}