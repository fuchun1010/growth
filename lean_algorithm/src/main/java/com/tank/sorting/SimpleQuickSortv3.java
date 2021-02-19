package com.tank.sorting;

/**
 * @author tank198435163.com
 */
public class SimpleQuickSortv3 {


  public int pivotIndex(int[] array, int start, int end) {
    int markIndex = start;
    int pivotValue = array[start];

    for (int index = start + 1; index <= end; index++) {
      int indexValue = array[index];
      if (indexValue < pivotValue) {
        markIndex++;
        int tmp = array[index];
        array[index] = array[markIndex];
        array[markIndex] = tmp;
      }
    }

    array[start] = array[markIndex];
    array[markIndex] = pivotValue;


    return markIndex;
  }
}
