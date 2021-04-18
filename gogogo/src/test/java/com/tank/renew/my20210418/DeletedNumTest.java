package com.tank.renew.my20210418;

import lombok.val;
import org.junit.jupiter.api.*;

/**
 * @author tank198435163.com
 */
@DisplayName("删除K个数字后剩余最小")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeletedNumTest {

  @Test
  @DisplayName("测试用例1")
  void remainingMin1() {
    val result = this.v1.remainingMin("1593214", 3);
    Assertions.assertEquals(1214, result);
  }

  @Test
  @DisplayName("测试用例2")
  void remainingMin2() {
    val result = this.v1.remainingMin("10", 1);
    Assertions.assertEquals(0, result);
  }

  @Test
  @DisplayName("测试用例3")
  void remainingMin3() {
    val result = this.v1.remainingMin("1593214", 1);
    Assertions.assertEquals(193214, result);
  }

  @BeforeEach
  void initialize() {
    this.v1 = new DeletedNumImpl();
  }

  private DeletedNum v1;
}