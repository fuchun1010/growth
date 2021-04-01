package com.tank.renew.my20210401;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @param <T>
 * @author tank198435163.com
 */
@Getter
@Setter
public class LifeNode<T> {

  public LifeNode(@NonNull final T data) {
    this.data = data;
  }

  public LifeNode<T> pre;

  public T data;

  public LifeNode<T> next;

}
