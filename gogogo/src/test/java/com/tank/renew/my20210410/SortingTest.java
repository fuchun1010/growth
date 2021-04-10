package com.tank.renew.my20210410;

import cn.hutool.core.util.ArrayUtil;
import lombok.val;
import org.junit.jupiter.api.*;

@DisplayName("计数排序")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SortingTest {

  @Test
  @DisplayName("朴素计数排序")
  void sortV1() {
    Assertions.assertNotNull(this.sortingV1);
    val result = this.sortingV1.sort(arr);
    Assertions.assertTrue(ArrayUtil.isNotEmpty(result));
    Assertions.assertEquals(3, result[0].intValue());
  }

  @Test
  @DisplayName("优化后的计数排序")
  void sortV2() {
    Assertions.assertNotNull(this.sortingV2);
    Integer[] arr = {90, 91, 82, 75, 99};
    val result = this.sortingV2.sort(arr);
    Assertions.assertTrue(ArrayUtil.isNotEmpty(result));
    Assertions.assertEquals(75, result[0].intValue());
  }

  @Test
  @DisplayName("处理并列数据的排序")
  void sortV3() {
    final Integer[] arr = {90, 99, 95, 94, 95};
    Assertions.assertNotNull(this.sortingV3);
    val result = this.sortingV3.sort(arr);
    Assertions.assertEquals(90, result[0].intValue());
    Assertions.assertEquals(94, result[1].intValue());
    Assertions.assertEquals(95, result[2].intValue());
    Assertions.assertEquals(95, result[3].intValue());
    Assertions.assertEquals(99, result[4].intValue());
  }

  @Test
  @DisplayName("处理并列数据的排序")
  void sortV4() {
    Assertions.assertNotNull(this.sortingV3);
    val result = this.sortingV3.sort(this.arr);
    Assertions.assertEquals(3, result[0].intValue());
    Assertions.assertEquals(4, result[1].intValue());
    Assertions.assertEquals(5, result[2].intValue());
    Assertions.assertEquals(7, result[3].intValue());
    Assertions.assertEquals(9, result[4].intValue());
  }

  @Test
  @DisplayName("桶排序")
  void sortV5() {
    Assertions.assertNotNull(this.bucketV1);
    Integer[] arr = {7, 5, 3, 4, 9, 1, 2};
    val result = this.bucketV1.sort(arr);
    Assertions.assertNotNull(result);
  }


  @Test
  @DisplayName("测试是否是冥等")
  void testPower() {
    val a = 2;
    val b = 3;
    val c = 4;
    Assertions.assertEquals(0, a & (a - 1));
    Assertions.assertNotEquals(0, b & (b - 1));
    Assertions.assertEquals(0, c & (c - 1));
  }

  @BeforeEach
  void initialize() {
    this.sortingV1 = new CounterSortingV1();
    this.sortingV2 = new CounterSortingV2();
    this.sortingV3 = new CounterSortingV3();
    this.bucketV1 = new BucketSortingV1();
  }

  private Sorting sortingV1;

  private Sorting sortingV2;

  private Sorting sortingV3;

  private Sorting bucketV1;

  private final Integer[] arr = {7, 5, 3, 4, 9};
}