package com.tank.renew.my20210325;

import lombok.NonNull;

/**
 * @param <T>
 * @author tank198435163.com
 */
public interface QueueDef<T> {

  T deQueue();

  void enQueue(@NonNull final T data);
}
