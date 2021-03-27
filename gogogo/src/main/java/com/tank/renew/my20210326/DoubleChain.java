package com.tank.renew.my20210326;

import lombok.NonNull;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class DoubleChain<T> {

  public void add(@NonNull final T data) {

  }

  public T delHeader() {
    return null;
  }

  private static class Node<T> {

    private T data;

    private Node<T> pre;

    private Node<T> next;
  }


}
