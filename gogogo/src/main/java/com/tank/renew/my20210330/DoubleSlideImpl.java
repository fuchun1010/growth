package com.tank.renew.my20210330;

import lombok.NonNull;


/**
 * @param <T>
 * @author tank198435163.com
 */
public class DoubleSlideImpl<T extends Comparable<T>> implements MarkIndex<T> {

  @Override
  public int splitIndex(@NonNull T[] arr, int start, int end) {
    T head = arr[start];
    int left = start;
    int right = end;

    while (left != right) {

      while (left < right && head.compareTo(arr[right]) < 0) {
        right--;
      }

      while (left < right && head.compareTo(arr[left]) >= 0) {
        left++;
      }

      if (left < right) {
        T tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
      }

    }

    arr[start] = arr[left];
    arr[left] = head;

    return left;
  }
}
