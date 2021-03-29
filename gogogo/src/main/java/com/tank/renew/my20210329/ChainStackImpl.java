package com.tank.renew.my20210329;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class ChainStackImpl<T> implements StackDef<T> {
  @Override
  public int size() {
    return this.counter.get();
  }

  @Override
  public void push(T data) {
    val newNode = new Node<T>();
    newNode.setData(data);
    if (Objects.isNull(head)) {
      this.head = newNode;
    } else {
      this.tail.next = newNode;
    }
    newNode.setNext(head);
    this.tail = newNode;
    this.counter.incrementAndGet();
  }

  @Override
  public T pop() {
    Preconditions.checkArgument(this.size() == 0,"not allowed pop empty stack");
    val pointer = this.head.next;
    this.tail.next = pointer;
    T data = this.head.getData();
    this.head.setNext(null);
    this.head.setPre(null);
    this.head = pointer;
    this.counter.decrementAndGet();
    return data;
  }

  @Override
  public void print() {
    if (this.size() == 0) {
      return;
    }
    var pointer = this.head;
    val lists = Lists.newLinkedList();
    do {
      lists.add(pointer.getData());
      pointer = pointer.next;
    } while (pointer != head);

    val joiner = new StringJoiner("->");
    lists.forEach(d -> joiner.add(d.toString()));
    System.out.println(joiner);
  }

  private Node<T> head;

  private Node<T> tail;

  @Getter
  @Setter
  @Accessors
  private static class Node<T> {

    private Node<T> pre;

    private Node<T> next;

    private T data;
  }

  private final AtomicInteger counter = new AtomicInteger(0);
  ;
}
