package com.tank.renew.my20210323;

import org.junit.jupiter.api.*;

import java.util.stream.IntStream;

/**
 * @author tank198435163.com
 */
@DisplayName("双向循环链表")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DoubleChainTest {

  @Test
  @DisplayName("添加双向链表元素")
  void add() {
    IntStream.rangeClosed(1, 10).forEach(this.doubleChain::add);
    Assertions.assertEquals(this.doubleChain.size(), 10);
  }

  @Test
  @DisplayName("打印双向链表")
  void print() {
    IntStream.rangeClosed(1, 10).forEach(this.doubleChain::add);
    Assertions.assertEquals(this.doubleChain.size(), 10);
    this.doubleChain.print();
  }

  @Test
  @DisplayName("删除双向链表元素")
  void delete() {
    IntStream.rangeClosed(1, 10).boxed().forEach(this.doubleChain::add);
    Assertions.assertEquals(this.doubleChain.size(), 10);
    this.doubleChain.print();
    this.doubleChain.delete(8);
    this.doubleChain.print();
    Assertions.assertEquals(this.doubleChain.size(), 9);
  }

  @BeforeEach
  void init() {
    this.doubleChain = new DoubleChainDef<>();
  }

  private DoubleChainDef<Integer> doubleChain;
}