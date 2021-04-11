package com.tank.renew.my20210411;

import lombok.val;
import org.junit.jupiter.api.*;

/**
 * @author tank198435163.com
 */
@DisplayName("检查循环链表")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoopChainTest {

  @Test
  @DisplayName("简化版的实现")
  void isLoopedV1() {
    val looped = this.v1.isLooped(this.root);
    Assertions.assertTrue(looped);
  }

  @Test
  @DisplayName("双指针判断是否是循环链表")
  void isLoopedV2() {
    val looped = this.v2.isLooped(this.root);
    Assertions.assertTrue(looped);
  }

  @BeforeEach
  void initialize() {
    this.v1 = new LoopChainValidateV1();
    this.v2 = new LoopChainValidateV2();
    this.root = new Node<>(1);
    Node<Integer> n2 = new Node<>(2);
    Node<Integer> n3 = new Node<>(3);
    Node<Integer> n4 = new Node<>(4);
    Node<Integer> n5 = new Node<>(5);
    Node<Integer> n6 = new Node<>(6);
    Node<Integer> n7 = new Node<>(7);

    this.root.setNext(n2);
    n2.setNext(n3);
    n3.setNext(n4);
    n4.setNext(n5);
    n5.setNext(n6);
    n6.setNext(n7);
    n7.setNext(n2);
  }

  private LoopChain v1;

  private LoopChain v2;

  private Node<Integer> root;
}