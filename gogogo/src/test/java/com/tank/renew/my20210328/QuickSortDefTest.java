package com.tank.renew.my20210328;

import lombok.NonNull;
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
    final Integer[] target = {5, 3, 4, 9, 8, 6, 1};
    final Integer[] result = this.quickSortDef.sort(this.target);
    Assertions.assertEquals(result.length, this.target.length);
    Assertions.assertEquals(result[0].intValue(), 1);
    Assertions.assertEquals(result[result.length - 1].intValue(), 9);
  }

  @Test
  @DisplayName("找一个短数组排序试试")
  void sort2() {
    final Integer[] array = {5, 3, 4, 9, 8};
    final Integer[] result = this.quickSortDef.sort(array);
    Assertions.assertEquals(result.length, this.target.length);
  }

  @Test
  @DisplayName("挑选基准index")
  void selectPivotIndex() {
    int[] array = {5, 3, 4, 9, 8};
    int markIndex = this.pivotIndex(array, 0, array.length);
    Assertions.assertEquals(markIndex, 2);
  }


  @BeforeEach
  @DisplayName("初始化排序")
  void initialize() {
    this.quickSortDef = new QuickSortV1Impl<>();
  }


  private int pivotIndex(@NonNull final int[] array, int startIndex, int endIndex) {

    int mark = startIndex;
    int head = array[startIndex];

    for (int index = startIndex + 1; index < endIndex; index++) {
      int c = array[index];
      if (c < head) {
        array[index] = array[index - 1];
        array[index - 1] = c;
        mark = index;
      }
    }

    return mark;
  }


  private QuickSortDef<Integer> quickSortDef;
  private final Integer[] target = {5, 3, 4, 9, 8, 6, 1, 2, 7};
}