package com.tank.renew.my20210330;

import lombok.NonNull;


/**
 * @param <T>
 * @author tank198435163.com
 */
public class DoubleSlideImpl<T extends Comparable<T>> implements MarkIndex<T> {

  @Override
  public int splitIndex(@NonNull T[] arr, int start, int end) {
    T pivot = arr[start];
    int left = start;
    int right = end;

    while (left != right) {

      while (left < right && pivot.compareTo(arr[right]) < 0) {
        right--;
      }

      while (left < right && pivot.compareTo(arr[left]) >= 0) {
        left++;
      }

      T tmp = arr[left];
      arr[left] = arr[right];
      arr[right] = tmp;
    }

    arr[start] = arr[left];
    arr[left] = pivot;

    return left;
  }
}
