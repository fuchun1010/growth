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

  @BeforeEach
  void initialize() {
    this.sortingV1 = new SortingV1();
    this.sortingV2 = new SortingV2();
  }

  private Sorting sortingV1;

  private Sorting sortingV2;

  private final Integer[] arr = {7, 5, 3, 4, 9};
}