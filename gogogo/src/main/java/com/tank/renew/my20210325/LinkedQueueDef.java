package com.tank.renew.my20210325;

import lombok.NonNull;

/**
 * @author tank198435163.com
 */
public class LinkedQueueDef<T> implements QueueDef<T> {
  @Override
  public T deQueue() {
    return null;
  }

  @Override
  public void enQueue(@NonNull T data) {

  }

  @Override
  public int size() {
    return 0;
  }
}
