package com.tank.sorting;

/**
 * @author tank198435163.com
 */
public class SimpleQuickSortV2 {

  public void sort(int[] array, int leftIndex, int rightIndex) {
    if (leftIndex >= rightIndex) {
      return;
    }
    int pivotIndex = this.pivotIndex(array, leftIndex, rightIndex);
    this.sort(array, leftIndex, pivotIndex - 1);
    this.sort(array, pivotIndex + 1, rightIndex);
  }

  public int pivotIndex(int[] array, int startIndex, int endIndex) {
    int leftIndex = startIndex;
    int rightIndex = endIndex;
    int pivot = array[startIndex];
    while (leftIndex != rightIndex) {
      while (leftIndex < rightIndex && pivot > array[rightIndex]) {
        rightIndex--;
      }
      while (leftIndex < rightIndex && pivot <= array[leftIndex]) {
        leftIndex++;
      }
      if (rightIndex != leftIndex) {
        int tmp = array[leftIndex];
        array[leftIndex] = array[rightIndex];
        array[rightIndex] = tmp;
      }
    }
    array[startIndex] = array[leftIndex];
    array[leftIndex] = pivot;

    return leftIndex;
  }

}
