package com.tank.algorithm;

import io.vavr.control.Option;
import lombok.NonNull;
import lombok.val;

/**
 * @author tank198435163.com
 */
public class TwoSumIntV2 {

  public Option<int[]> accumulateIndex(@NonNull final int[] array, int target) {

    int leftIndex = 0;

    int rightIndex = array.length - 1;

    while (leftIndex != rightIndex) {

      int left = array[leftIndex];
      int right = array[rightIndex];

      int sum = left + right;
      int differ = sum - target;
      if (differ == 0) {
        return Option.of(new int[]{leftIndex, rightIndex});
      }

      if (differ > 0) {
        if (left >= right) {
          leftIndex++;
        } else {
          rightIndex--;
        }
      } else {
        if (left >= right) {
          rightIndex--;
        } else {
          leftIndex++;
        }
      }

    }


    return Option.none();
  }

  public void quickSort(@NonNull final int[] array) {
    this.quickSort(array, 0, array.length - 1);
  }

  public void quickSort(@NonNull final int[] array, int startIndex, int endIndex) {
    if (startIndex >= endIndex) {
      return;
    }
    val pivotIndex = this.pivotIndex(array, startIndex, endIndex);
    this.quickSort(array, startIndex, pivotIndex - 1);
    this.quickSort(array, pivotIndex + 1, endIndex);
  }
  
  public int pivotIndex(@NonNull final int[] array, int startIndex, int endIndex) {
    int pivot = array[startIndex];
    int markIndex = startIndex;
    for (int index = startIndex + 1; index <= endIndex; index++) {
      val current = array[index];
      if (pivot > current) {
        markIndex++;
        int tmp = array[markIndex];
        array[index] = array[markIndex];
        array[markIndex] = tmp;
      }
    }

    array[startIndex] = array[markIndex];
    array[markIndex] = pivot;

    return markIndex;
  }
}
