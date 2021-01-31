package com.tank.algorithm;

import io.vavr.control.Option;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class SimpleQueue<T> {

  public SimpleQueue() {
    this(1 << 7);
  }

  public SimpleQueue(@NonNull int capacity) {
    this.capacity = this.capacitySize(capacity);
    this.head = new Node<>();
    this.next = this.head;
  }

  public void push(@NonNull final T data) {
    if (this.size == capacity) {
      return;
    }
    this.size++;
    val newNode = new Node<T>();
    newNode.setData(data);
    newNode.setLeft(this.next);
    this.next.right = newNode;
    this.next = newNode;
  }

  public int size() {
    return this.size;
  }

  public int capacity() {
    return this.capacity;
  }

  public Option<T> pop() {
    if (this.size == 0) {
      return Option.none();
    }
    this.head = this.head.right;
    val result = this.head.data;
    this.head = this.head.right;
    this.head.left.right = null;
    this.head.left = null;
    this.size--;
    return Option.of(result);
  }


  private int capacitySize(int capacity) {
    int result = capacity;
    result |= result >>> 1;
    result |= result >>> 4;
    result |= result >>> 8;
    result |= result >>> 16;
    return result <= 0 ? 1 : result + 1;
  }


  @Getter
  @Setter
  private static class Node<T> {

    private T data;

    private Node<T> left;

    private Node<T> right;
  }

  private int capacity = 0;

  private int size;

  private Class<T> clazz;

  private Node<T> head;

  private Node<T> next;
}
