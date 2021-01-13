package com.tank.algorithm.sorting;

import lombok.NonNull;
import lombok.val;

import java.util.Arrays;

/**
 * @author tank198435163.com
 */
public class MergeSorting {

  public int[] sort(@NonNull final int[] source) {
    int[] arr = Arrays.copyOf(source, source.length);

    if (arr.length < 2) {
      return arr;
    }

    val mid = (int) (Math.floor(arr.length / 2));


    int[] left = Arrays.copyOfRange(arr, 0, mid);
    int[] right = Arrays.copyOfRange(arr, mid, arr.length);

    return merge(sort(left), sort(right));
  }

  private int[] merge(int[] left, int[] right) {
    int[] result = new int[left.length + right.length];
    int i = 0;
    while (left.length > 0 && right.length > 0) {
      if (left[0] < right[0]) {
        result[i] = left[0];
        i++;
        left = Arrays.copyOfRange(left, 1, left.length);

      } else {
        result[i] = right[0];
        i++;
        right = Arrays.copyOfRange(right, 1, right.length);
      }
    }

    while (left.length > 0) {
      result[i] = left[0];
      i++;
      left = Arrays.copyOfRange(left, 1, left.length);
    }

    while (right.length > 0) {
      result[i] = right[0];
      i++;
      right = Arrays.copyOfRange(right, 1, right.length);
    }

    return result;

  }


}
