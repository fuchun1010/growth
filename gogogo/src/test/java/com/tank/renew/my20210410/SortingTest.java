package com.tank.renew.my20210410;

import cn.hutool.core.util.ArrayUtil;
import lombok.val;
import org.junit.jupiter.api.*;

@DisplayName("计数排序")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SortingTest {

  @Test
  void sortV1() {
    Assertions.assertNotNull(this.sortingV1);
    val result = this.sortingV1.sort(arr);
    Assertions.assertTrue(ArrayUtil.isNotEmpty(result));
    Assertions.assertEquals(3, result[0].intValue());
  }

  @BeforeEach
  void initialize() {
    this.sortingV1 = new SortingV1();
  }

  private Sorting sortingV1;

  private final Integer[] arr = {7, 5, 3, 4, 9};
}