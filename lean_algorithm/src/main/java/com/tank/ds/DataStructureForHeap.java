package com.tank.ds;

import lombok.NonNull;
import lombok.val;

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

  public void remove() {
    this.array[0] = this.array[size - 1];
    this.array[size - 1] = null;
    this.size--;
    this.siftDown(0);
  }

  private void siftDown(int index) {
    val half = this.size >> 1;
    T element = this.array[index];
    while (index < half) {
      int leftIndex = this.leftIndex(index);
      T leftValue = this.array[leftIndex];
      int rightIndex = this.rightIndex(index);
      if (rightIndex < size && this.array[rightIndex].compareTo(leftValue) > 0) {
        leftValue = this.array[leftIndex = rightIndex];
      }
      if (element.compareTo(leftValue) >= 0) {
        break;
      }
      this.array[index] = this.array[leftIndex];
      index = leftIndex;
    }
    this.array[index] = element;
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

  private int leftIndex(int index) {
    return (index << 1) + 1;
  }

  private int rightIndex(int index) {
    return (index << 1) + 2;
  }

  @SuppressWarnings("unchecked")
  private void resize() {
    this.capacity = this.capacity << 1;
    T[] tmp = ((T[]) Array.newInstance(this.clazz, this.capacity));
    System.arraycopy(this.array, 0, tmp, 0, this.array.length);
    this.array = tmp;
  }


  private int size = 0;

  private int capacity;

  private T[] array;

  private final Class<T> clazz;

}
