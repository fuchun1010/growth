package com.tank.renew.my20210419;

import lombok.val;
import org.junit.jupiter.api.*;

/**
 * 2个大整数相加
 *
 * @author tank198435163.com
 */
@DisplayName("2个大整数相加")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BigDataAddTest {

  @Test
  @DisplayName("测试用例1")
  void add() {
    val result = this.v1.add(first, second);
    Assertions.assertEquals("5221901005267", result);
  }

  @Test
  @DisplayName("测试用例2")
  void add2() {
    this.first = "88";
    this.second = "99";
    val result = this.v1.add(first, second);
    Assertions.assertEquals("187", result);
  }

  @Test
  @DisplayName("测试用例3")
  void add3() {
    this.first = "31";
    this.second = "152";
    val result = this.v1.add(first, second);
    Assertions.assertEquals("183", result);
  }

  @Test
  @Disabled
  @DisplayName("测试用例4")
  void add4() {
    this.first = "1";
    this.second = "999";
    val result = this.v1.add(first, second);
    Assertions.assertEquals("1000", result);
  }

  @BeforeEach
  void initialize() {
    this.v1 = new BigDataAddImpl();
  }

  private BigDataAdd v1;

  private String first = "426709752138";

  private String second = "95481253129";
}