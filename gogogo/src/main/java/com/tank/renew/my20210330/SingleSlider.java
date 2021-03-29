package com.tank.renew.my20210330;

import lombok.NonNull;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class SingleSlider<T extends Comparable<T>> implements MarkIndex<T> {

  @Override
  public int splitIndex(@NonNull final T[] arr, int start, int end) {
    final T head = arr[start];
    int mark = start;

    for (int index = start + 1; index < end; index++) {
      final T value = arr[index];
      if (value.compareTo(head) < 0) {
        mark++;
        T tmp = arr[index];
        arr[index] = arr[mark];
        arr[mark] = tmp;
      }
    }

    arr[start] = arr[mark];
    arr[mark] = head;

    return mark;
  }
}
