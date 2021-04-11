package com.tank.renew.my20210412;

import lombok.NonNull;

/**
 * @author tank198435163.com
 */
public interface MinStackDef<T extends Comparable<T>> {

  T pop();

  void push(@NonNull final T data);

  T min();
}
