package com.tank.renew.my20210412;

import lombok.NonNull;

import java.util.Stack;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class DoubleQueueImpl<T> implements DoubleQueue<T> {

  @Override
  public T pop() {
    synchronized (lock) {
      if (stackB.isEmpty()) {
        this.transfer();
      }
      return stackB.pop();
    }
  }

  @Override
  public void push(@NonNull T data) {
    this.stackA.push(data);
  }

  public void transfer() {
    synchronized (lock) {
      while (stackA.isEmpty()) {
        T target = stackA.pop();
        stackB.push(target);
      }
    }
  }

  private final Stack<T> stackA = new Stack<>();
  private final Stack<T> stackB = new Stack<>();
  private final byte[] lock = new byte[1];
}
