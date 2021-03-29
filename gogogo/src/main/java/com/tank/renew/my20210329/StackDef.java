package com.tank.renew.my20210329;

import lombok.NonNull;

/**
 * @author tank198435163.com
 */
public interface StackDef<T> {

  int size();

  void push(@NonNull final T data);

  T pop();

  void print();
}
