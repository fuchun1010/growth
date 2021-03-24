package com.tank.renew.my20210323;


import lombok.NonNull;
import lombok.val;
import lombok.var;

import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class DoubleChainDef<T> {

  public DoubleChainDef() {
    this.root = new Node<>(null);
    this.pointer = root;
  }


  public int size() {
    return this.size.get();
  }


  public void add(@NonNull final T data) {
    val newNode = new Node<T>(data);
    this.pointer.next = newNode;
    newNode.pre = this.pointer;
    this.pointer = newNode;
    this.size.incrementAndGet();
  }

  public void delete(@NonNull final T data) {
    Node<T> target = null;
    var tmpPointer = this.root;
    while (tmpPointer.next != null) {
      val nextNode = tmpPointer.next;
      if (nextNode.data.equals(data)) {
        target = nextNode;
        break;
      }
      tmpPointer = nextNode;
    }

    if (target != null) {
      target.pre.next = target.next;
      target.next.pre = target.pre;
      target.next = null;
      target.pre = null;
      this.size.decrementAndGet();
    }

  }


  public void insert(@NonNull final T target, @NonNull final T data) {
    Node<T> node = this.find(target);
    Node<T> newNode = new Node<>(data);
    newNode.next = node.next;
    node.next.pre = newNode;
    node.next = newNode;
    newNode.pre = node;
    this.size.incrementAndGet();
  }


  public void print() {
    var tmpPointer = this.root;
    val joiner = new StringJoiner(",");
    while (tmpPointer.next != null) {
      val nextNode = tmpPointer.next;
      joiner.add(nextNode.data.toString());
      tmpPointer = nextNode;
    }
    System.out.println(joiner.toString());
  }

  private Node<T> find(@NonNull final T data) {
    var tmpPointer = this.root;
    while (tmpPointer.next != null) {
      val nextNode = tmpPointer.next;
      if (nextNode.data.equals(data)) {
        return nextNode;
      }
      tmpPointer = nextNode;
    }
    return null;
  }


  private final Node<T> root;
  private Node<T> pointer;

  private final AtomicInteger size = new AtomicInteger();

  private static class Node<T> {

    public Node(final T data) {
      this.data = data;
    }

    public Node<T> pre;
    public T data;
    public Node<T> next;
  }
}
