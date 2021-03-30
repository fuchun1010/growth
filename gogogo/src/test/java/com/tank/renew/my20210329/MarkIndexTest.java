package com.tank.renew.my20210329;

import com.tank.renew.my20210330.DoubleSlideImpl;
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


  @Test
  @DisplayName("双指针切分寻找index2")
  void splitIndex2() {
    this.markIndex = new DoubleSlideImpl<>();
    val result = this.markIndex.splitIndex(this.arr, 0, this.arr.length - 1);
    Assertions.assertEquals(3, result);
  }

  @Test
  @DisplayName("双指针切分寻找index3")
  void splitIndex3() {
    this.markIndex = new DoubleSlideImpl<>();
    Integer[] arr = new Integer[]{10, 2, 1, 9, 6, 14, 3};
    val result = this.markIndex.splitIndex(arr, 0, this.arr.length - 1);
    Assertions.assertEquals(3, result);
  }

  @BeforeEach
  void initialize() {
    this.arr = new Integer[]{5, 4, 3, 9, 8, 6, 1};
    this.markIndex = new SingleSlider<>();
  }

  private Integer[] arr;

  private MarkIndex<Integer> markIndex;
}