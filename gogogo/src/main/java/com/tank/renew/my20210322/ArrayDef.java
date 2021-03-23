package com.tank.renew.my20210322;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.lang.reflect.Array;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tank198435163.com
 */
public class ArrayDef<T extends Comparable<T>> {

  @SuppressWarnings("unchecked")
  public ArrayDef(@NonNull Integer capacity, @NonNull final Class<T> clazz) {
    this.clazz = clazz;
    this.capacity = capacity;
    this.array = (T[]) Array.newInstance(clazz, capacity);
  }

  public ArrayDef(@NonNull final Class<T> clazz) {
    this(1 << 2, clazz);
  }

  public int size() {
    return this.index.get();
  }

  public void print() {
    StringJoiner joiner = new StringJoiner(",");
    for (int i = 0; i < this.index.get(); i++) {
      joiner.add(this.array[i].toString());
    }
    System.out.println(joiner.toString());
  }

  public void update(@NonNull Integer index, @NonNull T data) {
    if (index < 0 || index > this.capacity) {
      throw new IndexOutOfBoundsException("index out of range");
    }
    this.array[index] = data;
  }


  public void add(@NonNull T data) {
    if (this.index.get() == this.capacity) {
      resize();
    }
    this.array[this.index.get()] = data;

    this.index.incrementAndGet();
  }

  public boolean delete(@NonNull T data) {
    int ind = -1;
    for (int i = 0; i < this.array.length; i++) {
      if (this.array[i].compareTo(data) == 0) {
        ind = i;
        break;
      }
    }

    if (ind > -1) {
      return this.deleteBy(ind);
    }

    return false;
  }

  @SneakyThrows
  public boolean deleteBy(@NonNull Integer index) {
    int pointer = this.index.get();
    if (index < 0 || index > pointer) {
      throw new IllegalAccessException("index over validate range");
    }
    this.array[index] = null;
    System.arraycopy(this.array, index + 1, this.array, index, pointer - index);
    this.index.decrementAndGet();
    return true;

  }

  @SneakyThrows({IllegalAccessException.class})
  public T obtain(@NonNull Integer index) {
    if (index < 0 || index > capacity) {
      throw new IllegalAccessException("");
    }
    return this.array[index];
  }


  @SuppressWarnings("unchecked")
  private void resize() {
    this.capacity = this.capacity << 1;
    T[] newArray = (T[]) Array.newInstance(clazz, this.capacity);
    System.arraycopy(this.array, 0, newArray, 0, this.array.length);
    this.array = newArray;
  }


  private T[] array;

  private Integer capacity;

  private final Class<T> clazz;

  private final AtomicInteger index = new AtomicInteger(0);

}
