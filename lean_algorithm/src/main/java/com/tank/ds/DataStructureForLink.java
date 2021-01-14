package com.tank.ds;

import lombok.NonNull;
import lombok.val;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class DataStructureForLink<T> {

  public DataStructureForLink() {
    this.head = new NodeDefinition<>(null);
    this.tail = this.head;
  }

  public Integer add(@NonNull final T data) {
    synchronized (lock) {
      val node = new NodeDefinition<>(data);
      node.right = null;
      node.left = this.tail;
      this.tail.right = node;
      this.tail = node;
    }
    return counter.incrementAndGet();
  }

  public void traversal() {
    synchronized (this.lock) {
      NodeDefinition<T> tmp = this.head.right;
      while (tmp != null) {
        System.out.println(tmp.data.toString());
        tmp = tmp.right;
      }
    }
  }

  public int delete(@NonNull final T data) {
    NodeDefinition<T> tmp = this.head.right;
    if (tmp == null) {
      return 0;
    }

    //delete from head
    if (tmp.getData().equals(data)) {
      tmp.left = null;
      this.head.right = tmp.right;
      tmp.right = null;
      return this.counter.decrementAndGet();
    }

    //delete from tail
    if (this.tail.getData().equals(data)) {
      this.tail.left.right = null;
      this.tail.left = null;
      return this.counter.decrementAndGet();
    }

    do {
      if (tmp.getData().equals(data)) {
        val preNode = tmp.left;
        preNode.right = tmp.right;
        tmp.right.left = preNode;
        tmp.right = null;
        tmp.left = null;
        return this.counter.decrementAndGet();
      }
      tmp = tmp.right;
    } while (tmp != null);

    return 0;
  }


  public Integer size() {
    return this.counter.get();
  }


  private NodeDefinition<T> head;

  private NodeDefinition<T> tail;

  private final AtomicInteger counter = new AtomicInteger(0);

  private final byte[] lock = new byte[1];
}
