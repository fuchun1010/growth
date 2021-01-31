package com.tank.algorithm;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

/**
 * @author tank198435163.com
 */
public class SimpleStack<T> {

  public SimpleStack() {
    this.head = new Node<>();
  }

  public void add(@NonNull final T data) {
    synchronized (lock) {
      Objects.requireNonNull(this.head);
      val newNode = new Node<T>();
      newNode.data = data;
      if (Objects.isNull(head.right)) {
        head.right = newNode;
        newNode.left = head;
      } else {
        this.next.right = newNode;
        newNode.left = this.next;
      }
      this.next = newNode;
      ++this.size;
    }


  }

  public T pop() throws AccessDeniedException {
    synchronized (this.lock) {
      if (Objects.isNull(this.next) || Objects.isNull(this.next.left)) {
        throw new AccessDeniedException("stack is empty");
      }
      Node<T> result = this.next;
      Node<T> preNode = this.next.left;
      preNode.right = null;
      this.next.left = null;
      this.next = preNode;
      --this.size;
      return result.data;
    }
  }

  public int size() {
    synchronized (this.lock) {
      return this.size;
    }
  }


  @Getter
  @Setter
  private static class Node<T> {
    private Node<T> left;
    private Node<T> right;
    private T data;
  }

  private final Node<T> head;
  private Node<T> next;

  private int size;

  private final Byte[] lock = new Byte[1];

}
