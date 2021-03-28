package com.tank.renew.my20210328;

import org.junit.jupiter.api.*;

/**
 * @author tank198435163.com
 */
@DisplayName("快排测试")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuickSortDefTest {

  @Test
  @DisplayName("测试排序")
  void sort() {
    final Integer[] result = this.quickSortDef.sort(this.target);
    Assertions.assertEquals(result.length, this.target.length);
    Assertions.assertEquals(result[0].intValue(), 1);
    Assertions.assertEquals(result[result.length - 1].intValue(), 9);
  }

  @BeforeEach
  @DisplayName("初始化排序")
  void initialize() {
    this.quickSortDef = new QuickSortV1Impl<>();
  }


  private QuickSortDef<Integer> quickSortDef;
  private final Integer[] target = {5, 3, 4, 9, 8, 6, 1, 2, 7};
}