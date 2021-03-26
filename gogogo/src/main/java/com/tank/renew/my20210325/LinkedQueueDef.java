package com.tank.renew.my20210325;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tank198435163.com
 */
public class LinkedQueueDef<T> implements QueueDef<T> {

  public LinkedQueueDef() {
    super();
    this.root = new Item<>();
    this.pointer = this.root;
  }

  @Override
  public T deQueue() {
    this.counter.decrementAndGet();
    if (this.root.getData() == null) {
      this.root = this.root.next;
    }
    T data = this.root.data;
    val next = this.root.next;

    if (next == null) {
      return null;
    }

    this.root = next;
    if (next.pre.next != null) {
      next.pre.next = null;
    }
    next.pre = null;
    return data;
  }

  @Override
  public void enQueue(@NonNull T data) {
    val newItem = new Item<T>(data);
    this.pointer.next = newItem;
    newItem.pre = this.pointer;
    this.pointer = newItem;
    this.counter.incrementAndGet();
  }

  @Override
  public int size() {
    return this.counter.get();
  }

  private Item<T> root;
  private Item<T> pointer;
  private final AtomicInteger counter = new AtomicInteger(0);

  @Getter
  @Setter
  private static class Item<T> {

    public Item() {
      super();
    }

    public Item(@NonNull final T data) {
      this.data = data;
    }

    private Item<T> pre;

    private Item<T> next;

    private T data;
  }
}
