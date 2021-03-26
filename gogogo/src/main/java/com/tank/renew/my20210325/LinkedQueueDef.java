package com.tank.renew.my20210325;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;

import java.util.concurrent.atomic.AtomicBoolean;
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

    if (header.get()) {
      header.set(false);
      this.root = this.root.next;
    }

    if (this.root == null) {
      return null;
    }
    Item<T> next = this.root;

    T value = next.getData();
    next.pre = null;
    this.root = next.next;
    if (this.root == null) {
      return null;
    }
    this.root.pre = null;
    next.next = null;


    return value;
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
  private final AtomicBoolean header = new AtomicBoolean(true);

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
