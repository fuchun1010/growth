package com.tank.renew.my20210417;

import lombok.val;
import org.junit.jupiter.api.*;

/**
 * @author tank198435163.com
 */
@DisplayName("最大差值")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MaxDifferTest {

  @Test
  @DisplayName("测试用例1")
  void maxDistance() {
    int[] arr = {2, 6, 3, 4, 5, 10, 9};
    val result = this.v1.maxDistance(arr);
    Assertions.assertEquals(3, result);
  }

  @Test
  @DisplayName("测试用例2")
  void maxDistance2() {
    int[] arr = {42, 7, 1, 6};
    val result = this.v1.maxDistance(arr);
    Assertions.assertEquals(35, result);
  }

  @Test
  @DisplayName("步长")
  void step1() {
    //val index = (value - min) * (length - 1) / differ;
    val length = 100;
    val min = 10;
    val max = 100;
    val value = 70;
    val differ = max - min;
    val index = (value - min) * (length - 1) / differ;
    System.out.println(index);
  }

  @BeforeEach
  void initialize() {
    this.v1 = new MaxDifferImpl();
  }

  private MaxDiffer v1;
}