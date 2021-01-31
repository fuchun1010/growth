package com.tank.algorithm;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.lang.reflect.Array;
import java.nio.file.AccessDeniedException;

/**
 * @author tank198435163.com
 */
public class SimpleArray<T extends Comparable<T>> {

  @SuppressWarnings("unchecked")
  public SimpleArray(int capacity, Class<T> clazz) {
    this.array = ((T[]) Array.newInstance(clazz, capacity));
    this.capacity = capacity;
  }

  public SimpleArray(@NonNull final Class<T> clazz) {
    this(1 << 7, clazz);
  }

  public boolean add(@NonNull final T data) {
    if (this.size == capacity) {
      this.resize();
    }
    this.array[size++] = data;
    return true;
  }

  @SneakyThrows
  public T obtain(@NonNull final Integer index) {
    if (index < 0) {
      throw new AccessDeniedException("");
    }

    if (index >= this.size) {
      throw new AccessDeniedException("");
    }

    return this.array[index];
  }

  @SneakyThrows
  public boolean delete(@NonNull final T data) {
    if (this.size == 0) {
      throw new AccessDeniedException("array is empty");
    }
    int targetIndex = -1;
    int length = this.size;
    for (int index = 0; index < length; index++) {
      if (this.array[index].compareTo(data) == 0) {
        targetIndex = index;
        break;
      }
    }
    if (targetIndex == -1) {
      return false;
    }

    this.array[targetIndex] = null;

    if (targetIndex != length - 1) {
      for (int j = targetIndex + 1; j < length; j++) {
        this.array[j - 1] = this.array[j];
        this.array[j] = null;
      }
    }

    this.size--;
    return true;
  }

  public int size() {
    return this.size;
  }

  @SuppressWarnings("unchecked")
  private void resize() {
    this.capacity = this.capacity << 1;
    final T[] tmpArray = (T[]) Array.newInstance(this.clazz, this.capacity);
    System.arraycopy(this.array, 0, tmpArray, 0, this.size);
    this.array = tmpArray;
  }

  private T[] array;
  private int size = 0;
  private int capacity = 0;
  private Class<T> clazz;
}
