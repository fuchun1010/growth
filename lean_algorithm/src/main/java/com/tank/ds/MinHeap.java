package com.tank.ds;

import lombok.NonNull;

import java.lang.reflect.Array;

/**
 * @param <T>
 * @author tank198435163.com
 */
class MinHeap<T extends Comparable<T>> {

  public MinHeap(@NonNull final Class<T> clazz) {
    this(1 << 4, clazz);
  }

  public MinHeap(@NonNull final T[] array) {
    this.array = array;
  }

  @SuppressWarnings("unchecked")
  public MinHeap(int capacity, @NonNull final Class<T> clazz) {
    this.capacity = capacity;
    this.clazz = clazz;
    this.array = ((T[]) Array.newInstance(clazz, capacity));
  }

  public T min() {
    return this.array[0];
  }

  public void add(@NonNull T data) {
    if (this.size >= capacity) {
      this.resize();
    }
    this.array[this.size++] = data;
    this.siftUp(this.size - 1);
  }

  public void remove() {
    int lastIndex = this.size() - 1;
    this.array[0] = this.array[lastIndex];
    this.array[lastIndex] = null;
    this.size--;
    this.siftDown(0);
  }

  private void siftDown(int index) {
    T elementNode = this.array[index];
    int half = this.size >> 1;
    while (index < half) {
      int leftIndex = this.leftIndex(index);
      T leftNode = this.array[leftIndex];
      int rightIndex = this.rightIndex(index);
      if (rightIndex < size && this.array[rightIndex].compareTo(leftNode) <= 0) {
        leftNode = this.array[rightIndex];
        leftIndex = rightIndex;
      }
      if (leftNode.compareTo(elementNode) > 0) {
        return;
      }

      T tmp = this.array[index];
      this.array[index] = this.array[leftIndex];
      this.array[leftIndex] = tmp;
      index = leftIndex;
    }

  }

  private void swap(T t, T t1) {
    T tmp = t;
    t = t1;
    t1 = tmp;
  }

  public T[] obtain() {
    return this.array;
  }

  public int size() {
    return this.size;
  }

  public void siftUp(int index) {
    T childValue = this.array[index];
    while (index > 0) {
      int parentIndex = this.parentIndex(index);
      T parentNode = this.array[parentIndex];
      if (parentNode.compareTo(childValue) <= 0) {
        return;
      }
      T tmp = this.array[index];
      this.array[index] = parentNode;
      this.array[parentIndex] = tmp;
      index = parentIndex;
    }
  }

  private int parentIndex(int index) {
    return (index - 1) >> 1;
  }

  private int leftIndex(int index) {
    return (index << 1) + 1;
  }

  private int rightIndex(int index) {
    return (index << 1) + 2;
  }

  @SuppressWarnings("unchecked")
  private void resize() {
    T[] tmpArray = ((T[]) Array.newInstance(this.clazz, this.capacity << 1));
    System.arraycopy(this.array, 0, tmpArray, 0, this.array.length - 1);
    this.array = tmpArray;
    this.capacity = this.capacity << 1;
  }

  private int size;

  private int capacity;

  private Class<T> clazz;

  private T[] array;
}
