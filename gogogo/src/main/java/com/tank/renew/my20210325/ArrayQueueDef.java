package com.tank.renew.my20210325;

import lombok.NonNull;

import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tank198435163.com
 */
public class ArrayQueueDef<T> implements QueueDef<T> {

  @SuppressWarnings("unchecked")
  public ArrayQueueDef(@NonNull Class<T> clazz) {
    defaultSize = 1 << 4;
    this.arr = (T[]) Array.newInstance(clazz, defaultSize);
  }

  @Override
  public T deQueue() {
    if (this.size.get() == 0) {
      throw new IndexOutOfBoundsException("over index of array");
    }
    return null;
  }

  @Override
  public void enQueue(@NonNull T data) {
    throw new UnsupportedOperationException("enQueue not supported");
  }

  private final AtomicInteger size = new AtomicInteger(0);

  private T[] arr;

  private int defaultSize;
}
