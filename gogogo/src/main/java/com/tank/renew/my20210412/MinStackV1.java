package com.tank.renew.my20210412;

import lombok.NonNull;
import lombok.val;

import java.lang.reflect.Array;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class MinStackV1<T extends Comparable<T>> implements MinStackDef<T> {

  @SuppressWarnings("unchecked")
  public MinStackV1(int capacity, Class<T> clazz) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("capacity not allowed empty");
    }
    int cap = this.tableSizeFor(capacity);
    this.arr = (T[]) Array.newInstance(clazz, cap);
    this.minArr = (T[]) Array.newInstance(clazz, cap);
  }

  @Override
  public T pop() {
    if (this.index == 0) {
      return null;
    }

    T min = this.min();
    val value = this.arr[this.index - 1];
    if (min.compareTo(value) == 0) {
      this.minArr[--this.minIndex] = null;
    }
    val result = this.arr[--this.index];
    this.arr[this.index] = null;
    return result;

  }

  @Override
  public void push(@NonNull T data) {
    if (this.index == 0) {
      this.minArr[this.minIndex++] = data;
      this.minValue = data;
    }
    this.arr[this.index++] = data;
    if (this.minValue.compareTo(data) == 0) {
      return;
    }
    if (data.compareTo(this.minValue) < 0) {
      this.minArr[this.minIndex++] = data;
      this.minValue = data;
    }
  }

  @Override
  public T min() {
    if (this.minIndex == 0) {
      return null;
    }
    return this.minArr[this.minIndex - 1];
  }

  private int tableSizeFor(int capacity) {
    int result = capacity - 1;
    int maxValue = 1 << 30;
    result |= result >>> 1;
    result |= result >>> 2;
    result |= result >>> 4;
    result |= result >>> 8;
    result |= result >>> 16;
    return (result <= 0) ? 1 : result > maxValue ? maxValue : result + 1;
  }


  private int index = 0;

  private final T[] arr;

  private final T[] minArr;

  private int minIndex = 0;

  private T minValue;
}
