package com.tank.renew.my20210506;

import lombok.val;
import org.junit.jupiter.api.*;

@DisplayName("三数相加")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultThreeAddImplTest {

  @Test
  @DisplayName("三数据相加")
  void obtainIndexes() {
    val resultOpt = this.threeAdd.obtainIndexes(18, this.data);
    Assertions.assertTrue(resultOpt.isPresent());
    Assertions.assertTrue(resultOpt.get().length > 0);
    val result = resultOpt.get();
    Assertions.assertEquals(result[0], 0);
    Assertions.assertEquals(result[1], 5);
    Assertions.assertEquals(result[2], 7);
  }

  @Test
  @DisplayName("三数据相加2")
  void obtainIndexes2() {
    this.data = new int[]{9, 3, 2, 20, 18};
    val resultOpt = this.threeAdd.obtainIndexes(47, this.data);
    Assertions.assertTrue(resultOpt.isPresent());
    Assertions.assertTrue(resultOpt.get().length > 0);
    val result = resultOpt.get();
    Assertions.assertEquals(result[0], 2);
    Assertions.assertEquals(result[1], 3);
    Assertions.assertEquals(result[2], 4);
  }

  @Test
  @DisplayName("三数据相加2")
  void obtainIndexes3() {
    this.data = new int[]{9, 3, 2, 20, 18};
    val resultOpt = this.threeAdd.obtainIndexes(147, this.data);
    Assertions.assertFalse(resultOpt.isPresent());
  }


  @DisplayName("初始化")
  @BeforeEach
  void initialize() {
    this.threeAdd = new DefaultThreeAddImpl();
    this.data = new int[]{5, 3, 6, 1, 2, 9, 7, 10};
  }

  private ThreeAdd threeAdd;

  private int[] data;
}