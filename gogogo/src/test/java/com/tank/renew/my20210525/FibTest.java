package com.tank.renew.my20210525;

import lombok.val;
import org.junit.jupiter.api.*;

@DisplayName("斐波拉契数列")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FibTest {

  @Test
  @DisplayName("前2个计算")
  void calculateFib1() {
    val resultA = this.fib.calculate(0);
    val resultB = this.fib.calculate(1);
    val expected = 1;
    Assertions.assertEquals(expected, resultA);
    Assertions.assertEquals(expected, resultB);
  }

  @Test
  @DisplayName("计算第3个")
  void calculateFib2() {
    val resultA = this.fib.calculate(3);
    val expected = 3;
    Assertions.assertEquals(expected, resultA);
  }

  @Test
  @DisplayName("计算第4个")
  void calculateFib4() {
    val resultA = this.fib.calculate(4);
    val expected = 5;
    Assertions.assertEquals(expected, resultA);
  }

  @Test
  @DisplayName("计算第50个")
  void calculateFib100() {
    val resultA = this.fib.calculate(50);
    val expected = 3;
    Assertions.assertEquals(expected, resultA);
  }


  @BeforeEach
  void init() {
    this.fib = new DefaultFibImpl();
  }

  private Fib fib;
}