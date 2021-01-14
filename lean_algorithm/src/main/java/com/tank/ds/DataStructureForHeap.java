package com.tank.ds;

import lombok.NonNull;
import lombok.val;

import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class DataStructureForHeap<T extends Comparable<T>> {

  public DataStructureForHeap(@NonNull final Class<T> clazz) {
    this(1 << 3, clazz);
  }

  @SuppressWarnings("unchecked")
  public DataStructureForHeap(@NonNull final Integer capacity, @NonNull final Class<T> clazz) {
    this.capacity = capacity;
    this.array = ((T[]) Array.newInstance(clazz, capacity));
  }


  public void add(@NonNull T data) throws IllegalAccessException {
    val index = this.counter.get();
    if (index > this.array.length) {
      throw new IllegalAccessException("over array index");
    }
    this.array[index] = data;
    this.up(index);
    this.counter.incrementAndGet();
  }

  public T[] obtainArray() {
    return this.array;
  }

  public void up(@NonNull Integer index) {
    while (index > 0 && this.array[index].compareTo(this.array[parent(index)]) > 0) {
      this.swap(index, parent(index));
      index = parent(index);
    }
  }



  public void swap(@NonNull Integer i, @NonNull Integer j) {
    T tmp = this.array[i];
    this.array[i] = this.array[j];
    this.array[j] = tmp;
  }


  public int parent(@NonNull Integer index) {
    return (index - 1) / 2;
  }

  private int left(@NonNull Integer index) {
    return 2 * index + 1;
  }

  private int right(@NonNull final Integer index) {
    return 2 * index + 2;
  }


  private T[] array;

  private final int capacity;

  private final AtomicInteger counter = new AtomicInteger(0);

}
