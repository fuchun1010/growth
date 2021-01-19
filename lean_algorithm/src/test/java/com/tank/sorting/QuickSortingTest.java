package com.tank.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuickSortingTest {

  @Test
  void complexFastSort() {
    final Integer[] inputs = {5, 1, 6, 3};
    this.quickSorting.complexFastSort(inputs, 0, inputs.length - 1);
    Assertions.assertEquals(1, (int) inputs[0]);
  }

  @BeforeEach
  void initialize() {
    this.quickSorting = new QuickSorting<>();
  }

  private QuickSorting<Integer> quickSorting;
}