package com.tank.renew.my20210329;

import lombok.val;
import org.junit.jupiter.api.*;

import java.util.stream.IntStream;

/**
 * @author tank198435163.com
 */
@DisplayName("测试栈")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StackDefTest {

  @Test
  @DisplayName("测试入栈")
  void push() {
    Assertions.assertEquals(this.stackDef.size(), max);
    this.stackDef.print();
  }

  @Test
  @DisplayName("出栈")
  void pop() {
    val result = this.stackDef.pop();
    Assertions.assertEquals(1, result.intValue());
    Assertions.assertEquals(this.stackDef.size(), max - 1);
    this.stackDef.print();
  }

  @BeforeEach
  void initialize() {
    this.stackDef = new ChainStackImpl<>();
    IntStream.rangeClosed(1, max).boxed().forEach(this.stackDef::push);
  }

  private StackDef<Integer> stackDef;

  private final int max = 3;
}