package com.tank.renew.my20210525;

import lombok.val;
import org.junit.jupiter.api.*;

/**
 * @author tank198435163.com
 */
@DisplayName("斐波拉契数列v2测试")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FibV1ImplTest {

  @Test
  @DisplayName("1个长度")
  void calculate() {
    val result = this.fib.calculate(1);
    Assertions.assertEquals(1, result);
  }

  @Test
  @DisplayName("2个长度")
  void calculate2() {
    val result = this.fib.calculate(2);
    Assertions.assertEquals(1, result);
  }

  @Test
  @DisplayName("3个长度")
  void calculate3() {
    val result = this.fib.calculate(3);
    Assertions.assertEquals(2, result);
  }

  @Test
  @DisplayName("4个长度")
  void calculate4() {
    val result = this.fib.calculate(4);
    Assertions.assertEquals(3, result);
  }

  @BeforeEach
  void init() {
    this.fib = new FibV1Impl();
  }

  private Fib fib;
}