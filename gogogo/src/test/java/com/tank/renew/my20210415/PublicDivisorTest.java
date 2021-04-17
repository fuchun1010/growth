package com.tank.renew.my20210415;

import lombok.val;
import org.junit.jupiter.api.*;

@DisplayName("最大公约数")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PublicDivisorTest {

  @Test
  @DisplayName("欧几里得辗转相除1")
  void divisorV1() {
    val a = 7;
    val b = 2;
    val result = this.v1.divisor(a, b);
    Assertions.assertEquals(1, result);
  }

  @Test
  @DisplayName("欧几里得辗转相除12")
  void divisorV12() {
    val a = 12;
    val b = 8;
    val result = this.v1.divisor(a, b);
    Assertions.assertEquals(4, result);
  }

  @Test
  @DisplayName("欧几里得辗转相除2")
  void divisorV2() {
    val a = 7;
    val b = 2;
    val result = this.v2.divisor(a, b);
    Assertions.assertEquals(1, result);
  }

  @Test
  @DisplayName("欧几里得辗转相除21")
  void divisorV21() {
    val a = 12;
    val b = 8;
    val result = this.v2.divisor(a, b);
    Assertions.assertEquals(4, result);
  }

  @BeforeEach
  void initialize() {
    this.v1 = new PublicDivisorV1();
    this.v2 = new PublicDivisorV2();
  }

  private PublicDivisor v1;

  private PublicDivisor v2;


}