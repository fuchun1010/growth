package com.tank.renew.my20210326;

import lombok.NonNull;
import lombok.val;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tank198435163.com
 */
public class DoubleChain<K, V> {


  public void addCacheNode(@NonNull final K key,
                           @NonNull final V value) {
    if (Objects.isNull(head)) {
      head = new CacheNode<>();
      head.setKey(key);
      head.setValue(value);
      next = head;
      next.setNext(head);
    } else {
      val newCacheNode = new CacheNode<K, V>();
      newCacheNode.setKey(key);
      newCacheNode.setValue(value);
      next.setNext(newCacheNode);
      newCacheNode.setPrev(next);
      next = newCacheNode;
      newCacheNode.setNext(head);
    }
    counter.incrementAndGet();
  }

  public void delHead() {
    if (this.counter.get() == 0) {
      return;
    }
    val currentHead = this.next.getNext();

    if (currentHead == this.next) {
      this.head = null;
      this.next = null;
    } else {
      val newHead = currentHead.getNext();
      this.next.setNext(newHead);
      newHead.setPrev(null);
      currentHead.setNext(null);
      currentHead.setPrev(null);
      this.head = newHead;
    }

    this.counter.decrementAndGet();

  }

  public int size() {
    return counter.get();
  }


  private CacheNode<K, V> head;
  private CacheNode<K, V> next;

  private final AtomicInteger counter = new AtomicInteger(0);

}
