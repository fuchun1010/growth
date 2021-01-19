package com.tank.sorting;

import org.junit.jupiter.api.*;

@DisplayName("快排")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuickSortingTest {

  @Test
  void complexFastSort() {
    final Integer[] inputs = {5, 1, 6, 3};
    this.quickSorting.complexFastSort(inputs, 0, inputs.length - 1);
    Assertions.assertEquals(1, (int) inputs[0]);
  }

  @Test
  @DisplayName("单边pivot查找")
  void complexFastSortV2() {
    this.quickSorting.complexFastSortV2(this.inputs, 0, this.inputs.length - 1);
    Assertions.assertEquals(3, (int) this.inputs[0]);
    Assertions.assertEquals(5, (int) this.inputs[1]);
    Assertions.assertEquals(6, (int) this.inputs[2]);
    Assertions.assertEquals(8, (int) this.inputs[3]);
    Assertions.assertEquals(12, (int) this.inputs[4]);
  }

  @BeforeEach
  void initialize() {
    this.quickSorting = new QuickSorting<>();
    this.inputs = new Integer[]{6, 5, 8, 12, 3};
  }

  private QuickSorting<Integer> quickSorting;

  private Integer[] inputs;
}