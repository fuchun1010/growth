package com.tank.renew.my20210329;

import com.tank.renew.my20210330.MarkIndex;
import com.tank.renew.my20210330.SingleSlider;
import lombok.val;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MarkIndexTest {

  @Test
  @DisplayName("寻找切分index")
  void splitIndex() {
    val target = this.markIndex.splitIndex(this.arr, 0, this.arr.length);
    Assertions.assertEquals(3, target);
  }

  @BeforeEach
  void initialize() {
    this.arr = new Integer[]{5, 4, 3, 9, 8, 6, 1};
    this.markIndex = new SingleSlider<>();
  }

  private Integer[] arr;

  private MarkIndex<Integer> markIndex;
}