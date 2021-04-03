package com.tank.renew.my20210403;

import lombok.NonNull;
import lombok.val;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author tank198435163.com
 */
@DisplayName("heap定义测试")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HeapDefTest {

  @DisplayName("测试右节点")
  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2, 3})
  void testLeftIndex(@NonNull final int index) {
    val result = (index << 1) ^ 1;
    System.out.println("result = " + result);
    Assertions.assertTrue(result > 0);
  }

  @DisplayName("测试右节点")
  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4})
  void testRightIndex(@NonNull final int index) {
    val result = (index << 1) + 2;
    System.out.println("result = " + result);
    Assertions.assertTrue(result > 0);
  }

  @DisplayName("测试父节点")
  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4})
  void testParentIndex(@NonNull final int index) {
    val result = (index >> 1) - 1;
    System.out.println("result = " + result);
    Assertions.assertTrue(true);
  }

  @Test
  @DisplayName("构建堆")
  void buildHeap() {
    val result = this.heapDef.buildHeap(this.array, Integer.class);
    Assertions.assertEquals(result[0].intValue(), 11);
    Assertions.assertEquals(result[1].intValue(), 9);
    Assertions.assertEquals(result[2].intValue(), 2);
  }

  @Test
  @DisplayName("删除头节点")
  void removeRoot() {
    //{13, 11, 12, 26, 9, 24, 30, 28, 3, 7, 6, 29, 17, 18, 16, 10}
    this.heapDef.buildHeap(this.array, Integer.class);
    val result = this.heapDef.removeRoot();
    Assertions.assertEquals(result.intValue(), 11);
  }


  @BeforeEach
  void initialize() {
    this.heapDef = new HeapDefImpl<>();
  }

  private HeapDef<Integer> heapDef;

  private Integer[] array = {5, 9, 2, 11};
}