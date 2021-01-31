package com.tank.algorithm;

import io.vavr.control.Try;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.AccessDeniedException;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * @author tank198435163.com
 */
class SimpleStackTest {

  @Test
  @DisplayName("添加stack元素")
  void add() {
    this.process(stack -> Assertions.assertEquals(5, stack.size()));
  }

  @Test
  @DisplayName("测试出栈")
  void pop() {
    this.process(stack -> {
      val result = Try.of(this.stack::pop).getOrElse(-1);
      Assertions.assertEquals(5, (int) result);
    });
  }

  @Test
  @DisplayName("测试弹出空栈")
  void popExp() {
    this.process(stack -> {
      IntStream.rangeClosed(1, 5).forEach(i -> Try.of(stack::pop).getOrElse(-1));
      Assertions.assertThrows(AccessDeniedException.class, stack::pop);
    });
  }

  @Test
  @DisplayName("测试栈的长度")
  void size() {
    this.process(stack -> Assertions.assertEquals(5, stack.size()));
  }

  void process(Consumer<SimpleStack<Integer>> consumer) {
    consumer.accept(stack);
  }

  @BeforeEach
  void initialize() {
    this.stack = new SimpleStack<>();
    IntStream.rangeClosed(1, 5).boxed().forEach(this.stack::add);
  }

  private SimpleStack<Integer> stack;
}