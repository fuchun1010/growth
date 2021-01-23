package com.tank.spike;

import com.google.common.collect.Queues;
import io.vavr.collection.Stream;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@DisplayName("水平遍历tree")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TreeTraversalTest {

  @Test
  @DisplayName("队列构建测试")
  void testQueueDefinition() {
    val queue = new QueueDefinition<Integer>();
    val items = Arrays.asList(3, 4, 5, 1);
    Stream.ofAll(items).forEach(queue::push);
    Assertions.assertEquals(items.size(), queue.size());
    val head = queue.pop();
    Assertions.assertEquals(3, (int) head);
  }

  @Test
  @DisplayName("tree非递归遍历")
  void testBuildTree() {
    val root = new Node<String>();
    root.data = "A";

    val b = new Node<String>();
    b.data = "B";

    val c = new Node<String>();
    c.data = "c".toUpperCase();

    root.children.add(b);
    root.children.add(c);

    val d = new Node<String>();
    d.data = "d".toUpperCase();
    b.children.add(d);

    Assertions.assertEquals(2, root.children.size());

    root.traversal();
  }


  private static class QueueDefinition<T> {

    public QueueDefinition() {
      this(1 << 31);
    }

    public QueueDefinition(@NonNull final Integer capacity) {
      this.capacity = capacity;
      this.head = new Node<>();
      this.next = this.head;
    }

    public void push(@NonNull final T data) {
      if (size.get() == this.capacity) {
        return;
      }
      val node = new Node<>(data);
      this.next.next = node;
      this.next = node;
      this.size.incrementAndGet();
    }

    @SneakyThrows({IllegalAccessException.class})
    public T pop() {
      if (this.head.next == null) {
        throw new IllegalAccessException("queue is empty");
      }
      val result = this.head.next.data;
      this.head = this.head.next;
      return result;
    }

    public int size() {
      return this.size.get();
    }

    private final int capacity;

    private final AtomicInteger size = new AtomicInteger(0);

    private Node<T> head = null;
    private Node<T> next = null;

  }


  static class Node<T> {

    public Node() {
    }

    public Node(@NonNull T data) {
      this.data = data;
    }

    public void traversal() {
      this.queue.add(this);
      while (!this.queue.isEmpty()) {
        val result = this.queue.poll();
        System.out.println("value =" + result.data.toString());
        if (!result.children.isEmpty()) {
          this.queue.addAll(result.children);
        }
      }
    }

    private void traversal(Node<T> node) {

      if (node == null) {
        return;
      }

      this.queue.add(node);
      System.out.println("value = " + Objects.requireNonNull(this.queue.poll()).data.toString());
      this.queue.addAll(node.children);

    }

    public T data;

    public Node<T> next;

    public final List<Node<T>> children = new ArrayList<>();

    public Queue<Node<T>> queue = Queues.newArrayDeque();

  }


}
