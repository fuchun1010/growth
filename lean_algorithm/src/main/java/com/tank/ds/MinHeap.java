package com.tank.ds;

import lombok.NonNull;

import java.lang.reflect.Array;

/**
 * @param <T>
 * @author tank198435163.com
 */
class MinHeap<T extends Comparable<T>> {

  MinHeap(@NonNull final Class<T> clazz) {
    this(1 << 4, clazz);
  }

  @SuppressWarnings("unchecked")
  MinHeap(int capacity, @NonNull final Class<T> clazz) {
    this.capacity = capacity;
    this.clazz = clazz;
    this.array = ((T[]) Array.newInstance(clazz, capacity));
  }

  T min() {
    return this.array[0];
  }

  void add(@NonNull T data) {
    //TODO process resize
    this.array[this.size++] = data;
    this.siftUp(this.size - 1);
  }

  private void siftUp(int index) {
    T childValue = this.array[index];
    while (index > 0) {
      int parentIndex = this.parentIndex(index);
      T parentNode = this.array[parentIndex];
      if (childValue.compareTo(parentNode) > 0) {
        return;
      }
      T tmp = this.array[parentIndex];
      this.array[parentIndex] = this.array[index];
      this.array[index] = tmp;
      index = parentIndex;
    }
  }

  private int parentIndex(int index) {
    return (index - 1) >>> 1;
  }

  private int size;

  private int capacity;

  private Class<T> clazz;

  private T[] array;
}
