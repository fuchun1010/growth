package com.tank.algorithm;

import lombok.val;
import org.junit.jupiter.api.*;

@DisplayName("尝试2数相加的第二种方式")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TwoSumIntV2Test {

  @Test
  void accumulateIndex() {
    final int[] array = {1, 2, 3, 4, 7, 9};
    val resultOpt = this.twoSumIntV2.accumulateIndex(array, 9);
    Assertions.assertTrue(resultOpt.isDefined());
  }

  @Test
  void accumulateIndex2() {
    final int[] array = {1, 2, 3, 4, 7, 9};
    val resultOpt = this.twoSumIntV2.accumulateIndex(array, 3);
    Assertions.assertTrue(resultOpt.isDefined());
  }

  @Test
  void accumulateIndex3() {
    final int[] array = {4, 1, 2, 3, 7, 9};
    this.twoSumIntV2.quickSort(array);
    val resultOpt = this.twoSumIntV2.accumulateIndex(array, 3);
    Assertions.assertTrue(resultOpt.isDefined());
  }

  @Test
  @DisplayName("2数之和测试用例4")
  void accumulateIndex4() {
    final int[] array = {4, 1, 2, 3, 7, 9};
    this.twoSumIntV2.quickSort(array);
    val resultOpt = this.twoSumIntV2.accumulateIndex(array, 11);
    Assertions.assertTrue(resultOpt.isDefined());
  }

  @Test
  @DisplayName("2数之和测试不存在测试用例")
  void accumulateIndex5() {
    final int[] array = {4, 1, 2, 3, 7, 9};
    this.twoSumIntV2.quickSort(array);
    val resultOpt = this.twoSumIntV2.accumulateIndex(array, 50);
    Assertions.assertTrue(resultOpt.isEmpty());
  }

  @Test
  @DisplayName("2数之和测试用例6")
  void accumulateIndex6() {
    final int[] array = {8, 1, 50, 2, 10, 13};
    this.twoSumIntV2.quickSort(array);
    val resultOpt = this.twoSumIntV2.accumulateIndex(array, 51);
    Assertions.assertTrue(resultOpt.isDefined());
  }

  @Test
  @DisplayName("测试基准值index")
  void pivotIndex() {
    final int[] array = {4, 1, 2, 3, 7, 9};
    val result = this.twoSumIntV2.pivotIndex(array, 0, array.length - 1);
    Assertions.assertEquals(3, result);
  }

  @Test
  @DisplayName("快排测试")
  void quickSort() {
    final int[] array = {4, 1, 2, 3, 7, 9};
    this.twoSumIntV2.quickSort(array, 0, array.length - 1);
    Assertions.assertEquals(1, array[0]);
  }


  @BeforeEach
  void initialize() {
    this.twoSumIntV2 = new TwoSumIntV2();
  }

  private TwoSumIntV2 twoSumIntV2;
}