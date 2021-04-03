package com.tank.renew.my20210403;

import com.google.common.base.Preconditions;
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
    synchronized (this.lock) {
      T[] tmpArray = (T[]) Array.newInstance(clazz, array.length);
      for (T value : array) {
        tmpArray[this.size++] = value;
        this.siftUp(tmpArray, this.size - 1);
      }
      this.result = tmpArray;
      return tmpArray;
    }
  }

  @Override
  public T removeRoot() {
    Preconditions.checkArgument(this.size() != 0, "heap is empty");
    T value = this.result[0];
    this.result[0] = this.result[this.size - 1];
    this.result[this.size - 1] = null;
    this.size--;
    this.siftDown(this.result, 0);
    return value;
  }

  private void siftDown(T[] result, int index) {
    T target = result[index];
    while (index < this.size) {
      int leftIndex = this.leftIndex(index);
      int rightIndex = this.rightIndex(index);
      if (rightIndex > this.size) {
        return;
      }
      T leftValue = this.result[leftIndex];
      T rightValue = this.result[rightIndex];
      int maxIndex = leftValue.compareTo(rightValue) >= 0 ? leftIndex : rightIndex;
      T maxValue = this.result[maxIndex];
      if (maxValue.compareTo(target) > 0) {
        T tmp = result[maxIndex];
        result[maxIndex] = result[index];
        result[index] = tmp;
      }
      index = maxIndex;
    }
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

  private T[] result;
}
