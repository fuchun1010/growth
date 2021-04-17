package com.tank.renew.my20210412;

import lombok.NonNull;

public interface DoubleQueue<T> {

  T pop();

  void push(@NonNull final T data);

}
