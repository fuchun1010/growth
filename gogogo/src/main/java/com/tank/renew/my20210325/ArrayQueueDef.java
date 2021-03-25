package com.tank.renew.my20210325;

import lombok.NonNull;
import lombok.val;

import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tank198435163.com
 */
public class ArrayQueueDef<T> implements QueueDef<T> {

  @SuppressWarnings("unchecked")
  public ArrayQueueDef(@NonNull Class<T> clazz) {
    defaultSize = 1 << 2;
    this.clazz = clazz;
    this.arr = (T[]) Array.newInstance(clazz, defaultSize);
  }

  @Override
  public T deQueue() {
    if (this.size.get() == 0) {
      throw new IndexOutOfBoundsException("over index of array");
    }

    int index = this.head.get();

    T data = this.arr[index];
    this.arr[index] = null;

    if (this.size.decrementAndGet() == 0) {
      this.arr = null;
      this.head.set(0);
    } else {
      this.head.incrementAndGet();
    }
    return data;
  }

  @Override
  public void enQueue(@NonNull T data) {
    int index = this.size.get();
    if (index == defaultSize) {
      this.resize();
    }
    this.arr[index] = data;
    this.size.incrementAndGet();
  }

  @Override
  public int size() {
    return this.size.get();
  }


  @SuppressWarnings("unchecked")
  private void resize() {
    this.defaultSize <<= 1;
    val tmpArr = (T[]) Array.newInstance(clazz, defaultSize);
    System.arraycopy(this.arr, 0, tmpArr, 0, this.arr.length);
    this.arr = tmpArr;
  }

  private final AtomicInteger size = new AtomicInteger(0);
  private final AtomicInteger head = new AtomicInteger(0);

  private T[] arr;

  private int defaultSize;

  private Class<T> clazz;
}
