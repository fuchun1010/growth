package com.tank.ds;

import lombok.NonNull;

import java.lang.reflect.Array;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class DataStructureForHeap<T extends Comparable<T>> {

  public DataStructureForHeap(@NonNull final Class<T> clazz) {
    this(1 << 4, clazz);
  }

  @SuppressWarnings("unchecked")
  public DataStructureForHeap(int capacity, Class<T> clazz) {
    this.clazz = clazz;
    this.capacity = capacity;
    this.array = ((T[]) Array.newInstance(clazz, this.capacity));
  }

  public void add(@NonNull final T data) {
    if (this.size >= capacity) {
      this.resize();
    }
    this.array[size++] = data;
    this.siftUp(size - 1);
  }

  public T obtain() {
    return this.array[0];
  }

  private void siftUp(int index) {
    T childValue = this.array[index];
    while (index > 0) {
      int parentIndex = this.parentIndex(index);
      T parentValue = this.array[parentIndex];
      if (childValue.compareTo(parentValue) < 0) {
        return;
      }
      T tmp = this.array[index];
      this.array[index] = this.array[parentIndex];
      this.array[parentIndex] = tmp;
      index = parentIndex;
    }
  }

  private int parentIndex(int index) {
    return (index - 1) >>> 1;
  }

  @SuppressWarnings("unchecked")
  private void resize() {
    this.capacity = this.capacity << 1;
    T[] tmp = ((T[]) Array.newInstance(this.clazz, this.capacity));
    System.arraycopy(this.array, 0, tmp, 0, this.array.length);
    this.array = tmp;
  }


  private int size = 0;

  private int capacity = 0;

  private T[] array;

  private Class<T> clazz;

}
