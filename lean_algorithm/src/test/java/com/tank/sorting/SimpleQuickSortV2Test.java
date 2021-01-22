package com.tank.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author tank198435163.com
 */
class SimpleQuickSortV2Test {

  @Test
  void sort() {
    SimpleQuickSortV2 simpleQuickSortV2 = new SimpleQuickSortV2();
    int[] array = {4, 2, 9, 5, 7, 1};
    simpleQuickSortV2.sort(array, 0, array.length - 1);
    Assertions.assertEquals(9, array[0]);
  }
}