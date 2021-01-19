package com.tank.sorting;

/**
 * @author tank198435163.com
 */
public class QuickSorting<T extends Comparable<T>> {


  public void complexFastSort(T[] array, int startIndex, int endIndex) {
    if (startIndex >= endIndex) {
      return;
    }
    int pivotIndex = this.partition(array, startIndex, endIndex);
    complexFastSort(array, startIndex, pivotIndex - 1);
    complexFastSort(array, pivotIndex + 1, startIndex);
  }

  private int partition(T[] array, int startIndex, int endIndex) {
    T pivot = array[startIndex];
    int leftIndex = startIndex;
    int rightIndex = endIndex;

    while (leftIndex != rightIndex) {
      while (leftIndex < rightIndex && array[rightIndex].compareTo(pivot) > 0) {
        rightIndex--;
      }
      while (leftIndex < rightIndex && array[leftIndex].compareTo(pivot) <= 0) {
        leftIndex++;
      }
      if (leftIndex < rightIndex) {
        T tmp = array[leftIndex];
        array[leftIndex] = array[rightIndex];
        array[rightIndex] = tmp;
      }
    }

    array[startIndex] = array[leftIndex];
    array[leftIndex] = pivot;

    return leftIndex;
  }

}
