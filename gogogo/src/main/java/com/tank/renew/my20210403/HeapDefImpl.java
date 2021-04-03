package com.tank.renew.my20210403;

import lombok.NonNull;
import lombok.val;

import java.lang.reflect.Array;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class HeapDefImpl<T extends Comparable<T>> implements HeapDef<T> {

  @Override
  public int size() {
    synchronized (this.lock) {
      return this.size;
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public T[] buildHeap(@NonNull final T[] array, @NonNull final Class<T> clazz) {
    T[] tmpArray = (T[]) Array.newInstance(clazz, array.length);
    for (T value : array) {
      tmpArray[this.size++] = value;
      this.siftUp(tmpArray, this.size - 1);
    }
    return tmpArray;
  }

  private void siftUp(@NonNull final T[] arr, int index) {
    T target = arr[index];
    while (index > 0) {
      val parentIndex = this.parentIndex(index);
      val parentValue = arr[parentIndex];
      if (target.compareTo(parentValue) > 0) {
        arr[parentIndex] = target;
        arr[index] = parentValue;
      }
      index = parentIndex;
    }
  }

  private int leftIndex(@NonNull final Integer index) {
    return (index << 1) ^ 1;
  }

  private int rightIndex(@NonNull final Integer index) {
    return (index << 1) + 2;
  }

  private int parentIndex(@NonNull final Integer index) {
    return (index - 1) >> 1;
  }


  private int size = 0;

  private final byte[] lock = new byte[1];
}
